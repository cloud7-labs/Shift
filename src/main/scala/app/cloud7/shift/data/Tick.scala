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

/**
 * Represents a single data point or event in a financial time series.
 *
 * A `Tick` captures the basic information of a financial instrument at a specific point in time.
 */
trait Tick {
  val timestamp: Long
  val symbol: String
  val price: Double

  override def toString: String =
    s"Tick(timestamp=$timestamp, symbol=$symbol, price=$price)"
}

object Tick {

  /**
   * @return an empty [[Tick]].
   */
  def empty: Tick = new Tick {
    override val timestamp: Long = 0L
    override val symbol: String = ""
    override val price: Double = 0.0
  }
}

/**
 * Simple implementation of [[Tick]].
 *
 * @param timestamp the timestamp.
 * @param symbol the symbol.
 * @param price the price.
 */
case class TickImpl(timestamp: Long, symbol: String, price: Double) extends Tick
