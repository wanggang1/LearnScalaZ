package org.gwgs.learningscalaz.day05

object BalancedPoolWalk {
  import scalaz._
  import Scalaz._
  
  type Birds = Int
  
  case class Pole(left: Birds, right: Birds) {
    def landLeft(n: Birds): Option[Pole] =
      if (math.abs((left + n) - right) < 4) copy(left = left + n).some
      else none
    def landRight(n: Birds): Option[Pole] =
      if (math.abs(left - (right + n)) < 4) copy(right = right + n).some
      else none
    def banana: Option[Pole] = none
  }

}