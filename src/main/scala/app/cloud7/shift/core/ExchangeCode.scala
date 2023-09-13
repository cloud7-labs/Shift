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
 * Represents an exchange code.
 */
sealed trait ExchangeCode

/**
 * Companion object for [[ExchangeCode]].
 */
object ExchangeCode {

  /**
   * Represents the New York Stock Exchange.
   */
  case object NYSE extends ExchangeCode

  /**
   * Represents the NASDAQ.
   */
  case object NASDAQ extends ExchangeCode

  /**
   * Represents the American Stock Exchange.
   */
  case object AMEX extends ExchangeCode

  /**
   * Represents the Australian Securities Exchange.
   */
  case object ASX extends ExchangeCode

  /**
   * Represents the BATS Global Markets.
   */
  case object BATS extends ExchangeCode

  /**
   * Represents the Canadian Securities Exchange.
   */
  case object CSE extends ExchangeCode

  /**
   * Represents the London Stock Exchange.
   */
  case object LSE extends ExchangeCode

  /**
   * Represents the Nasdaq Mutual Fund Quotation System.
   */
  case object NMFQS extends ExchangeCode

  /**
   * Represents the NYSE Arca exchange platform.
   */
  case object NYSEARCA extends ExchangeCode

  /**
   * Represents the NYSE MKT stock exchange.
   */
  case object NYSEMKT extends ExchangeCode

  /**
   * Represents the NYSE National platform.
   */
  case object NYSENAT extends ExchangeCode

  /**
   * Represents the OTC Bulletin Board.
   */
  case object OTCBB extends ExchangeCode

  /**
   * Represents the Other OTC (formerly known as Pink Sheets).
   */
  case object OTCCE extends ExchangeCode

  /**
   * Represents the Grey Market of OTC stocks.
   */
  case object OTCGREY extends ExchangeCode

  /**
   * Represents the OTC Markets Group.
   */
  case object OTCMKTS extends ExchangeCode

  /**
   * Represents the OTCQB market tier.
   */
  case object OTCQB extends ExchangeCode

  /**
   * Represents the OTCQX market tier.
   */
  case object OTCQX extends ExchangeCode

  /**
   * Represents the Pink Sheets market.
   */
  case object PINK extends ExchangeCode
}
