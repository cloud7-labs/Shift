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
 * The Rule trait represents a generic rule or condition
 * that can be applied to objects of type T.
 *
 * @tparam T The type of object the rule will be applied to.
 */
trait Rule[T] {

  /**
   * Checks if the given object satisfies the rule.
   *
   * @param t The object to evaluate against the rule.
   * @return true if the object satisfies the rule, false otherwise.
   */
  def isSatisfiedBy(t: T): Boolean

  /**
   * Combines this rule with another using logical AND.
   *
   * @param other The other rule to combine with.
   * @return A new rule that represents the logical AND of the two rules.
   */
  def and(other: Rule[T]): Rule[T] =
    AndRule(this, other)

  /**
   * Combines this rule with another using logical OR.
   *
   * @param other The other rule to combine with.
   * @return A new rule that represents the logical OR of the two rules.
   */
  def or(other: Rule[T]): Rule[T] =
    OrRule(this, other)

  /**
   * Negates this rule using logical NOT.
   *
   * @return A new rule that represents the logical NOT of this rule.
   */
  def not(): Rule[T] = NotRule(this)
}

/**
 * Represents a composite rule that is satisfied when
 * both input rules are satisfied.
 *
 * @param rule1 The first input rule.
 * @param rule2 The second input rule.
 * @tparam T The type of object the rule will be applied to.
 */
final case class AndRule[T](rule1: Rule[T], rule2: Rule[T]) extends Rule[T] {

  /**
   * Evaluates the logical AND of the input rules on the given object.
   *
   * @param t The object to evaluate against the composite rule.
   * @return true if the object satisfies both input rules, false otherwise.
   */
  override def isSatisfiedBy(t: T): Boolean =
    rule1.isSatisfiedBy(t) && rule2.isSatisfiedBy(t)
}

/**
 * Represents a composite rule that is satisfied when
 * either input rule is satisfied.
 *
 * @param rule1 The first input rule.
 * @param rule2 The second input rule.
 * @tparam T The type of object the rule will be applied to.
 */
final case class OrRule[T](rule1: Rule[T], rule2: Rule[T]) extends Rule[T] {

  /**
   * Evaluates the logical OR of the input rules on the given object.
   *
   * @param t The object to evaluate against the composite rule.
   * @return true if the object satisfies at least one of the input rules, false otherwise.
   */
  override def isSatisfiedBy(t: T): Boolean =
    rule1.isSatisfiedBy(t) || rule2.isSatisfiedBy(t)
}

/**
 * Represents a negated rule.
 *
 * @param rule The input rule to negate.
 * @tparam T The type of object the rule will be applied to.
 */
final case class NotRule[T](rule: Rule[T]) extends Rule[T] {

  /**
   * Evaluates the negation of the input rule on the given object.
   *
   * @param t The object to evaluate against the negated rule.
   * @return true if the object does not satisfy the input rule, false otherwise.
   */
  override def isSatisfiedBy(t: T): Boolean = !rule.isSatisfiedBy(t)
}
