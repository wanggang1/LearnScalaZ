package org.gwgs.learningscalaz.day08

import scalaz._
import Scalaz._

object MonadicOperations {

  /**
    * In Scalaz join (and its symbolic alias μ) is a method introduced by Bind:
    *
        trait BindOps[F[_],A] extends Ops[F[A]] {
          ...
          def join[B](implicit ev: A <~< F[B]): F[B] = F.bind(self)(ev(_))
          def μ[B](implicit ev: A <~< F[B]): F[B] = F.bind(self)(ev(_))
          ...
        }
    */

    val flattenedSome: Option[Int] = (Some(9.some): Option[Option[Int]]).join
    val flattenedNone = (Some(none): Option[Option[Int]]).join
    val flattenedList = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)).join

  /**
    * filterM, foldLeftM
    */

  def demo: Unit = {

    println(flattenedSome)

  }

}
