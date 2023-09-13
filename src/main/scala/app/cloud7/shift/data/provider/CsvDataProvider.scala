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

package app.cloud7.shift.data.provider

import akka.stream.IOResult
import akka.stream.alpakka.csv.scaladsl.CsvParsing
import akka.stream.scaladsl.{FileIO, Source}
import app.cloud7.shift.data.{Formatter, Tick}

import java.nio.file.Paths
import scala.concurrent.Future

/**
 * @param filePath the path to the CSV file.
 * @param delimiter the delimiter used in the CSV file.
 * @param dataFormatter the formatter used to format the data.
 */
final case class CsvDataProvider(filePath: String,
                                 delimiter: Char = ',',
                                 dataFormatter: Formatter[List[String], Tick]
) extends DataProvider[List[String]] {
  override protected val formatter: Formatter[List[String], Tick] = dataFormatter

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
