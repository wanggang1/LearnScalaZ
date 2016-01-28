package org.gwgs.learningscalaz

import org.gwgs.learningscalaz.day00.Polymorphism
import org.gwgs.learningscalaz.day01.Typeclasses102Equal
import org.gwgs.learningscalaz.day01.YesNoTypeclass
import org.gwgs.learningscalaz.day02.ApplicativeOps

object Main {
  
  def main(args: Array[String]) = {
    
    println("============== Scalaz ==================")
    import scalaz.Scalaz._
    val a = List(1,2) |+| List(3,4)
    println("Scalaz |+| " + a) 
    println("") 
    
    Polymorphism.demo
    
    Typeclasses102Equal.demo
    
    YesNoTypeclass.demo
    
    ApplicativeOps.demo
  }
 
}