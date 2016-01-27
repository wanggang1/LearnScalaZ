package org.gwgs.learningscalaz.day01

import scalaz._
import Scalaz._

object YesNoTypeclass {
  
  trait CanTruthy[A] { self =>
    /** @return true, if `a` is truthy. */
    def truthys(a: A): Boolean
  }
  
  object CanTruthy {
    def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
    
    def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
      def truthys(a: A): Boolean = f(a)
    }
  }
  
  trait CanTruthyOps[A] {
    def self: A
    implicit def F: CanTruthy[A]
    final def truthy: Boolean = F.truthys(self)
  }
  
  object ToCanIsTruthyOps {
    import scala.language.implicitConversions
    
    implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
      new CanTruthyOps[A] {
        def self = v
        implicit def F: CanTruthy[A] = ev
      }
    
    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
      if (cond.truthy) ifyes
      else ifno
  }

}