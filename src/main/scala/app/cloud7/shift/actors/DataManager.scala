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

package app.cloud7.shift.actors

import akka.actor.typed.*
import akka.actor.typed.scaladsl.Behaviors
import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import app.cloud7.shift.core.Backtest

/**
 * Manages data operations.
 */
object DataManager {
  sealed trait Command
  case class Start(backtests: List[Backtest[_]]) extends Command

  def apply()(implicit materializer: Materializer): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case Start(backtests) =>
          context.log.info(s"Starting ${backtests.size} backtests")
          backtests.foreach { backtest =>
            val actor =
              context.spawn(StrategyExecutor(backtest.strategy), backtest.strategy.uuid)

            val sink = Sink.foreach {
              actor ! StrategyExecutor.OnTick(_)
            }
            backtest.dataProvider.read.runWith(sink)

          }
          Behaviors.same
      }

    }
}
