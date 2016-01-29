package org.gwgs.learningscalaz.day07

object worksheet {
  println("Day 7, Applicative Builder")           //> Day 7, Applicative Builder
  
  import StatefulComputation._
  
  stackManip(List(5, 8, 2, 1))                    //> res0: scalaz.State[List[Int],Int] = scalaz.IndexedStateT$$anon$10@3cef309d
}