package org.gwgs.learningscalaz.day02

import scalaz._
import Scalaz._
  
object ApplicativeOps {
  import scala.language.higherKinds
  
  def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
    case Nil     => (Nil: List[A]).point[F]
    case x :: xs => (x |@| sequenceA(xs)) {_ :: _} 
  }

}