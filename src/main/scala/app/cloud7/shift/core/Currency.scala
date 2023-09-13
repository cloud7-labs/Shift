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
 * Represents a currency.
 */
sealed trait Currency {

  /**
   * Returns the exchange rate of this currency to the given currency.
   *
   * @param currency the currency.
   * @return the exchange rate.
   */
  def exchangeRate(currency: Currency): BigDecimal

}

/**
 * Companion object for [[Currency]].
 */
object Currency {

  /**
   * Represents the US Dollar.
   */
  case object USD extends Currency {

    override def exchangeRate(currency: Currency): BigDecimal = currency match {
      case USD => 1.0
      case HKD => 7.83
      case EUR => 1.07
      case AUD => 0.93
      case CNY => 7.28
    }
  }

  /**
   * Represents the Hong Kong Dollar.
   */
  case object HKD extends Currency {
    override def exchangeRate(currency: Currency): BigDecimal = currency match {
      case USD => 0.13
      case HKD => 1.0
      case EUR => 0.12
      case AUD => 0.2
      case CNY => 0.92
    }
  }

  /**
   * Represents the Euro.
   */
  case object EUR extends Currency {
    override def exchangeRate(currency: Currency): BigDecimal = currency match {
      case USD => 1.07
      case HKD => 8.40
      case EUR => 1.0
      case AUD => 1.67
      case CNY => 7.81
    }
  }

  /**
   * Represents the Australian dollar.
   */
  case object AUD extends Currency {
    override def exchangeRate(currency: Currency): BigDecimal = currency match {
      case USD => 0.64
      case HKD => 5.02
      case EUR => 0.6
      case AUD => 1.0
      case CNY => 4.67
    }
  }

  /**
   * Represents the Chinese Yuan.
   */
  case object CNY extends Currency {
    override def exchangeRate(currency: Currency): BigDecimal = currency match {
      case USD => 0.14
      case HKD => 1.08
      case EUR => 0.13
      case AUD => 0.21
      case CNY => 1.0
    }
  }
}
