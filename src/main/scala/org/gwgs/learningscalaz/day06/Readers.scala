package org.gwgs.learningscalaz.day06

object Readers {
  import scalaz.Scalaz._
  
  val addStuff: Int => Int = for {
     a <- (_: Int) * 2
     b <- (_: Int) + 10
   } yield a + b
       
}