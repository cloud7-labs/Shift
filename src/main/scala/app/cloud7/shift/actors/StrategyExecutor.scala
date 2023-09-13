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

import akka.actor.typed.Behavior
import app.cloud7.shift.core.Strategy
import app.cloud7.shift.data.Tick

/**
 * Executes a strategy.
 */
object StrategyExecutor {
  sealed trait Command
  case class OnTick[T <: Tick](tick: T) extends Command

  def apply[T <: Tick](strategy: Strategy[T]): Behavior[Command] = ???
}
