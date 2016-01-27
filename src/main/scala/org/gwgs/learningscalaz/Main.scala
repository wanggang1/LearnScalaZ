package org.gwgs.learningscalaz

import scalaz.Scalaz._

object Main {
  
  def main(args: Array[String]) = {
    val a = List(1,2) |+| List(3,4)
    println("a -> " + a) 
    
    val b = List(1,2) ++ List(3,4)
    println("b -> " + b) 
  }
 
}