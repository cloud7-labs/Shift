/*
 * Copyright 2023 cloud7
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.cloud7.shift.data

import akka.stream.IOResult
import akka.stream.alpakka.csv.scaladsl.CsvParsing
import akka.stream.scaladsl.{FileIO, Source}
import app.cloud7.shift.utils.Logging

import java.nio.file.Paths
import scala.concurrent.Future
import scala.util.Try

/**
 * Reads data from a source and formats it.
 *
 * @tparam A the type of the raw data
 * @tparam B the type of the formatted data
 */
trait Reader[A, B] extends Logging {
  protected val formatter: Formatter[A, B]

  /**
   * The raw source of data.
   *
   * @return the raw source of data
   */
  def rawSource: Source[A, ?]

  /**
   * Reads the data from the source and formats it.
   *
   * @return the formatted source of data
   */
  final def read: Source[B, ?] = rawSource.map(a =>
    Try(formatter.format(a)).fold(
      e => {
        logger.error(s"Failed to format data: $a", e)
        throw e
      },
      identity
    )
  )
}

/**
 * Companion object for [[Reader]].
 */
object Reader {

  /**
   * Reads data from a CSV file and formats it.
   *
   * @param filePath the path to the CSV file
   * @param delimiter the delimiter used in the CSV file
   * @param dataFormatter the formatter used to format the data
   * @tparam T the type of the formatted data
   * @return the formatted source of data
   */
  def readCsv[T](filePath: String,
                 delimiter: Char = ',',
                 dataFormatter: Formatter[List[String], T]
  ): Reader[List[String], T] =
    new Reader[List[String], T] {
      override protected val formatter: Formatter[List[String], T] = dataFormatter

      /**
     * The raw source of data.
     *
     * @return the raw source of data
     */
      override def rawSource: Source[List[String], Future[IOResult]] = FileIO
        .fromPath(Paths.get(filePath))
        .via(CsvParsing.lineScanner(delimiter.toByte))
        .map(_.map(_.utf8String))
    }
}
