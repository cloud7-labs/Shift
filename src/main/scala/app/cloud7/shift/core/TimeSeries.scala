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

sealed trait Frequency

object Frequency {
  case object Minute extends Frequency
  case object Hourly extends Frequency
  case object Daily extends Frequency
  case object Weekly extends Frequency
  case object Monthly extends Frequency
  case object Yearly extends Frequency
}

/**
 * Represents a time series.
 *
 * @param data the data.
 * @tparam T the type of the data.
 */
case class TimeSeries[T](private val data: List[(Long, T)]) {

  /**
   * Gets a value at a given time.
   *
   * @param time the time.
   * @return the value.
   */
  def at(time: Long): Option[T] = data.find(_._1 == time).map(_._2)

  /**
   * Gets a slice of the time series.
   *
   * @param start the start time.
   * @param end the end time.
   * @return the slice.
   */
  def slice(start: Long, end: Long): TimeSeries[T] = TimeSeries(data.filter {
    case (time, _) => time >= start && time <= end
  })

  /**
   * Resamples the time series.
   *
   * @param frequency the frequency.
   * @return the resampled time series.
   */
  def resample(frequency: Frequency): TimeSeries[T] = {
    val intervalMillis: Long = frequency match {
      case Frequency.Minute  => 60 * 1000
      case Frequency.Hourly  => 60 * 60 * 1000
      case Frequency.Daily   => 24 * 60 * 60 * 1000
      case Frequency.Weekly  => 7 * 24 * 60 * 60 * 1000
      case Frequency.Monthly => 30 * 24 * 60 * 60 * 1000
      case Frequency.Yearly  => 365 * 24 * 60 * 60 * 1000
    }

    /**
     * @param timestamp the timestamp.
     * @return the binned timestamp.
     */
    def binTimestamp(timestamp: Long): Long =
      (timestamp / intervalMillis) * intervalMillis

    val binnedData = data.groupBy { case (timestamp, _) => binTimestamp(timestamp) }

    val forwardFilledData = binnedData
      .map { case (binnedTimestamp, list) =>
        // Get the most recent value within the bin
        val mostRecentValue = list.sortBy(_._1).last._2
        (binnedTimestamp, mostRecentValue)
      }
      .toList
      .sortBy(_._1)

    TimeSeries(forwardFilledData)
  }
}
