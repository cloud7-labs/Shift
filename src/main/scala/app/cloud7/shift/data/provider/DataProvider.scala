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

import app.cloud7.shift.data.{Formatter, Reader, Tick}

/**
 * Data provider that reads data from a source and formats it to [[Tick]].
 *
 * @tparam A the type of the raw data
 */
trait DataProvider[A] extends Reader[A, Tick] {
  override protected val formatter: Formatter[A, Tick]
}

/**
 * Companion object for [[DataProvider]].
 */
object DataProvider {

  /**
   * @param filePath the path to the CSV file.
   * @param delimiter the delimiter used in the CSV file.
   * @param dataFormatter the formatter used to format the data.
   * @return the data provider.
   */
  def fromCsv(filePath: String,
              delimiter: Char = ',',
              dataFormatter: Formatter[List[String], Tick]
  ): DataProvider[List[String]] = CsvDataProvider(filePath, delimiter, dataFormatter)
}
