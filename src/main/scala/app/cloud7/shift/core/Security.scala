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

package app.cloud7.shift.core

/**
 * Represents the ticker symbol of a financial instrument.
 *
 * @param ticker the ticker symbol.
 */
case class Symbol(ticker: String) {
  override def toString: String = s"Symbol(ticker=$ticker)"
}

/**
 * Companion object for [[Symbol]].
 */
object Symbol {

  /**
   * @return an empty symbol.
   */
  def empty: Symbol = Symbol("")
}

/**
 * Represents the type of security of a financial instrument.
 */
sealed trait SecurityType

/**
 * Companion object for [[SecurityType]].
 */
object SecurityType {

  /**
   * Represents an equity.
   */
  case object Equity extends SecurityType

  /**
   * Represents an option.
   */
  case object Option extends SecurityType

  /**
   * Represents a future.
   */
  case object Future extends SecurityType

  /**
   * Represents a forex.
   */
  case object Forex extends SecurityType

  /**
   * Represents a crypto.
   */
  case object Crypto extends SecurityType
}

/**
 * Represents a security.
 *
 * @param symbol the symbol.
 * @param securityType the security type.
 * @param market the market.
 * @param exchangeCode the exchange code.
 */
final case class Security(
    symbol: Symbol,
    securityType: Option[SecurityType],
    market: Option[Market],
    exchangeCode: Option[ExchangeCode],
    currency: Option[Currency]
) {
  override def toString: String =
    s"Security(symbol=$symbol, securityType=$securityType, market=$market, exchangeCode=$exchangeCode, " +
      s"currency=$currency)"
}

/**
 * Companion object for [[Security]].
 */
object Security {

  /**
   * @return an empty security.
   */
  def empty: Security = Security(Symbol.empty, None, None, None, None)
}
