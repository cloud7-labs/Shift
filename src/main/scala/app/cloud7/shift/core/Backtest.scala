package app.cloud7.shift.core

import app.cloud7.shift.data.Tick
import app.cloud7.shift.data.provider.DataProvider

case class Backtest[T <: Tick](strategy: Strategy[T], dataProvider: DataProvider[T])
