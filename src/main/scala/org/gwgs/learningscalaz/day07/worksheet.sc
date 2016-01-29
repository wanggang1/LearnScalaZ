package org.gwgs.learningscalaz.day07

object worksheet {
  println("Day 7, Applicative Builder")           //> Day 7, Applicative Builder

  import scalaz._
  import Scalaz._
   
  import StatefulComputation._
  
  stackManip(List(5, 8, 2, 1))                    //> java.lang.IncompatibleClassChangeError: Found class scalaz.IndexedStateT, bu
                                                  //| t interface was expected
                                                  //| 	at org.gwgs.learningscalaz.day07.StatefulComputation$.stackManip(Statefu
                                                  //| lComputation.scala:18)
                                                  //| 	at org.gwgs.learningscalaz.day07.worksheet$$anonfun$main$1.apply$mcV$sp(
                                                  //| org.gwgs.learningscalaz.day07.worksheet.scala:11)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at org.gwgs.learningscalaz.day07.worksheet$.main(org.gwgs.learningscalaz
                                                  //| .day07.worksheet.scala:3)
                                                  //| 	at org.gwgs.learningscalaz.day07.worksheet.main(org.gwgs.learningscalaz.
                                                  //| day07.worksheet.scala)
}