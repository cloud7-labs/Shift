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
 * Represents a position.
 *
 * @param security the security.
 * @param quantity the quantity.
 * @param price the price.
 * @param targetWeight the target weight.
 * @param currentWeight the current weight.
 */
case class Position(
    security: Security,
    quantity: Int,
    price: Double,
    targetWeight: Double,
    currentWeight: Double
) {
  override def toString: String =
    s"Position(security=$security, quantity=$quantity, price=$price)"
}

/**
 * Companion object for [[Position]].
 */
object Position {

  /**
   * @return an empty position.
   */
  def empty: Position = Position(Security.empty, 0, 0.0, 0.0, 0.0)
}

/**
 * Represents a portfolio.
 *
 * @param cash the cash.
 * @param positions the positions.
 */
case class Portfolio(
    cash: Cash,
    positions: Map[Symbol, Position]
) {

  /**
   * Calculates the total value of the portfolio.
   *
   * @return the total value.
   */
  def totalValue: Cash = {
    val positionValues = positions.values.map { position =>
      Cash(position.quantity * position.price)
    }
    positionValues.foldLeft(cash)(_ + _)
  }

  /**
   * Calculates the total weight of the portfolio.
   *
   * @return the total weight.
   */
  def totalWeight: Double = {
    val positionWeights = positions.values.map { position =>
      position.currentWeight
    }
    positionWeights.foldLeft(0.0)(_ + _)
  }

  /**
   * Calculates the market value of the portfolio.
   *
   * @return the market value.
   */
  def marketValue: Cash = {
    val positionValues = positions.values.map { position =>
      Cash(position.quantity * position.price)
    }
    positionValues.foldLeft(cash)(_ + _)
  }

  override def toString: String = s"Portfolio(cash=$cash, positions=$positions)"
}

/**
 * Companion object for [[Portfolio]].
 */
object Portfolio {

  /**
   * @return an empty portfolio.
   */
  def empty: Portfolio = Portfolio(Cash.empty, Map.empty)
}
