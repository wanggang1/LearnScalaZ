package org.gwgs.learningscalaz.day08

import scalaz._
import Scalaz._

object MakingMonads {

  /*
   * Scalaz:
   *   Functor[F[_]]
   *   Monad[F[_]]
   *   Equal[T]
   *   Show[T]
   */
  trait ProbInstances {

    def flatten[B](xs: Prob[Prob[B]]): Prob[B] = {
      def multall(innerxs: Prob[B], p: Double) =
        innerxs.list map { case (x, r) => (x, p * r) }
      Prob((xs.list map { case (innerxs, p) => multall(innerxs, p) }).flatten)
    }

    implicit val probInstance = new Functor[Prob] with Monad[Prob] {
      def point[A](a: => A): Prob[A] = Prob((a, 1.0) :: Nil)
      def bind[A, B](pa: Prob[A])(f: A => Prob[B]): Prob[B] = flatten(map(pa)(f))
      override def map[A, B](fa: Prob[A])(f: A => B): Prob[B] =
        Prob(fa.list map { case (x, p) => (f(x), p) })
    }

    implicit def probShow[A]: Show[Prob[A]] = Show.showA

    /*
    implicit val listInstance = new Functor[List] with Monad[List] {
      def point[A](a: => A): List[A] = List(a)
      def bind[A, B](xs: List[A])(f: A => List[B]): List[B] = xs flatMap f
      override def map[A, B](xs: List[A])(f: A => B): List[B] = xs map f
    }
    */

  }

  case class Prob[A](list: List[(A, Double)])
  case object Prob extends ProbInstances

  sealed trait Coin
  case object Heads extends Coin
  case object Tails extends Coin
  implicit val coinEqual: Equal[Coin] = Equal.equalA

  def coin: Prob[Coin] = Prob(Heads -> 0.5 :: Tails -> 0.5 :: Nil)

  def loadedCoin: Prob[Coin] = Prob(Heads -> 0.1 :: Tails -> 0.9 :: Nil)

  def flipThree: Prob[Boolean] = for {
    a <- coin
    b <- coin
    c <- loadedCoin
  } yield { List(a, b, c) all {_ === Tails} }

  def demo: Unit = {
    val probs = Prob((3, 0.5) :: (5, 0.25) :: (9, 0.25) :: Nil)

    val pns = probs map {-_}

    println(pns)

    println(flipThree)
  }

}
