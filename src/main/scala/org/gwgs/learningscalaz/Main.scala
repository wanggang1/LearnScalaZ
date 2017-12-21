package org.gwgs.learningscalaz

import org.gwgs.learningscalaz.day00._
import org.gwgs.learningscalaz.day01._
import org.gwgs.learningscalaz.day02._
import org.gwgs.learningscalaz.day03._
import org.gwgs.learningscalaz.day07._
import org.gwgs.learningscalaz.day08._
import org.gwgs.learningscalaz.day11._

object Main {
  
  def main(args: Array[String]): Unit = {
    
    println("============== Scalaz ==================")
    import scalaz.Scalaz._
    val a = List(1,2) |+| List(3,4)
    println("Scalaz |+| concatenation: " + a)
    println("") 
    
    //Polymorphism.demo
    
    //Typeclasses102Equal.demo
    
    //YesNoTypeclass.demo
    
    //ApplicativeOps.demo
    
    //TaggedType.demo
    
    //StatefulComputation.demo
    
    //ScalazEither.demo
    
    //ScalazValidation.demo

    //RPNCalculator.demo

    //ComposeMonadicFunction.demo

    //MakingMonads.demo

    ScalazLens.demo
  }
 
}