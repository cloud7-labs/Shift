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

package app.cloud7.shift

/**
 * Represents a cash amount.
 *
 * @param value the cash.
 * @param currency the currency.
 */
case class Cash(value: BigDecimal, currency: Currency = Currency.USD) {

  /**
   * Converts this cash amount to the given currency.
   *
   * @param currency the currency.
   * @return the converted cash amount.
   */
  def convertTo(currency: Currency): Cash = {
    val rate = this.currency.exchangeRate(currency)
    Cash(this.value * rate, currency)
  }

  def +(that: Cash): Cash = Cash(this.value + that.value)

  def +(that: BigDecimal): Cash = Cash(this.value + that)

  def -(that: Cash): Cash = Cash(this.value - that.value)

  def -(that: BigDecimal): Cash = Cash(this.value - that)

  def *(that: Cash): Cash = Cash(this.value * that.value)

  def *(that: BigDecimal): Cash = Cash(this.value * that)

  def /(that: Cash): Cash = Cash(this.value / that.value)

  def /(that: BigDecimal): Cash = Cash(this.value / that)

  def >(that: Cash): Boolean = this.value > that.value

  def >(that: BigDecimal): Boolean = this.value > that

  def <(that: Cash): Boolean = this.value < that.value

  def <(that: BigDecimal): Boolean = this.value < that

  def >=(that: Cash): Boolean = this.value >= that.value

  def >=(that: BigDecimal): Boolean = this.value >= that

  def <=(that: Cash): Boolean = this.value <= that.value

  def <=(that: BigDecimal): Boolean = this.value <= that

  override def toString: String = s"Cash($value)"
}

/**
 * Companion object for [[Cash]].
 */
object Cash {

  /**
   * @return an empty cash amount.
   */
  def empty: Cash = Cash(0.0)
}
