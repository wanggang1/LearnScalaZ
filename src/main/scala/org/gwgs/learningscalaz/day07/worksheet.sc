package org.gwgs.learningscalaz.day07

object worksheet {
  println("Day 7, Applicative Builder")           //> Day 7, Applicative Builder

  import scalaz._
  import Scalaz._
   
  import StatefulComputation._
  
  stackManip(List(5, 8, 2, 1))                    //> res0: scalaz.Id.Id[(org.gwgs.learningscalaz.day07.StatefulComputation.Stack,
                                                  //|  Int)] = (List(8, 2, 1),5)
}