package org.gwgs.learningscalaz.day02

import scalaz._
import Scalaz._
  
object ApplicativeOps {
  import scala.language.higherKinds

  /**
   * implicit Applicative[Monoid[_]] provided by scalaz.Applicative
   */
  def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
    case Nil     => (Nil: List[A]).point[F]
    case x :: xs => (x |@| sequenceA(xs)) {_ :: _} 
  }

  /**
   * scalaz Allpy.lift2
   */
  val concatInOption = Apply[Option].lift2((_: Int) :: (_: List[Int]))

  def demo = {
    println("============== Applicative ===============")
                                                 
    println("scalaz Allpy.lift2 - concatInOption(3.some, List(4, 5).some): " + concatInOption(3.some, List(4, 5).some) )
    println("scalaz Allpy.lift2 - concatInOption(none[Int], List(4, 5).some): " + concatInOption(none[Int], List(4, 5).some) ) 
        
    println("Custom sequence - sequenceA(List(1.some, 2.some)): " + sequenceA(List(1.some, 2.some)) )
    println("Custom sequence - sequenceA(List(1.some, none, 2.some)): " + sequenceA(List(1.some, none, 2.some)) )
    println("Custom sequence - sequenceA(List(List(1, 2, 3), List(4, 5, 6))): " + sequenceA(List(List(1, 2, 3), List(4, 5, 6))) )

    println("")
  }
  
}