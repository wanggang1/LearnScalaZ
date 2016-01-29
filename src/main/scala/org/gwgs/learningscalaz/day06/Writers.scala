package org.gwgs.learningscalaz.day06

object Writers {
  import scalaz._
  import Scalaz._
  
  def isBigGang(x: Int): (Boolean, String) =
    (x > 9, "Compared gang size to 9.")
    
  implicit class PairOps[A](pair: (A, String)) {
    def applyLog[B](f: A => (B, String)): (B, String) = {
      val (x, log) = pair
      val (y, newlog) = f(x)
      (y, log ++ newlog)
    }
  }
  
}