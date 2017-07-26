package org.gwgs.learningscalaz.day08

object RPNCalculator {

  import scalaz._
  import Scalaz._

  def foldingFunction(list: List[Double], next: String): List[Double] =
    (list, next) match {
      case (x :: y :: ys, "*") => (y * x) :: ys
      case (x :: y :: ys, "+") => (y + x) :: ys
      case (x :: y :: ys, "-") => (y - x) :: ys
      case (xs, numString) => numString.toInt :: xs
    }

  def solveRPN(s: String): Double =
    (s.split(' ').toList.foldLeft(Nil: List[Double]) {foldingFunction}).head


  /*
   * scalaz provides:
   *   .point[Option] (or .some)
   *   .parseInt: Validation[NumberFormatException, Int]
   *   .foldLeftM
   */
  def foldingFunction_M(list: List[Double], next: String): Option[List[Double]] =
    (list, next) match {
      case (x :: y :: ys, "*") => ((y * x) :: ys).point[Option]
      case (x :: y :: ys, "+") => ((y + x) :: ys).some
      case (x :: y :: ys, "-") => ((y - x) :: ys).point[Option]
      case (xs, numString) => numString.parseInt.toOption.map(_ :: xs)
    }

  def solveRPN_M(s: String): Option[Double] =
    for {
      List(x) <- s.split(' ').toList.foldLeftM(Nil: List[Double]) {foldingFunction_M}
    } yield x


  def demo: Unit = {

    val result = solveRPN("10 4 3 + 2 * -")
    println(result)

    val result2 = solveRPN_M("10 4 3 + 2 * -")
    println(result2)
  }

}
