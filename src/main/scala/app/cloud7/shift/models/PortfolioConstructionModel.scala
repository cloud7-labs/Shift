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

package app.cloud7.shift.models

import app.cloud7.shift.Portfolio

/**
 * Represents a portfolio construction model.
 */
trait PortfolioConstructionModel {
  def constructPortfolio(portfolio: Portfolio): Portfolio

  /**
   * Normalizes the weights of the portfolio.
   *
   * @param portfolio the portfolio.
   * @return the normalized portfolio.
   */
  def normalizeWeights(portfolio: Portfolio): Portfolio = {
    portfolio.copy(positions = portfolio.positions.map { case (symbol, position) =>
      symbol -> position.copy(currentWeight =
        position.currentWeight / portfolio.positions.size
      )
    })
  }

  /**
   * Rebalances the portfolio.
   *
   * @param portfolio the portfolio.
   * @return the rebalanced portfolio.
   */
  def rebalance(portfolio: Portfolio): Portfolio = {
    val normalizedPortfolio = normalizeWeights(portfolio)
    normalizedPortfolio.copy(positions = normalizedPortfolio.positions.map {
      case (symbol, position) =>
        symbol -> position.copy(currentWeight =
          position.currentWeight / portfolio.totalWeight
        )
    })
  }
}
