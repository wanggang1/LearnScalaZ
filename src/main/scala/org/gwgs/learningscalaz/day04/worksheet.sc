package org.gwgs.learningscalaz.day04

import scalaz._
import Scalaz._

object worksheet {
  println("day 4")                                //> day 4
  
  //Functor laws
  
  //Identity
  List(1, 2, 3) map { identity } assert_=== List(1, 2, 3)
  
  //Associative
  (List(1, 2, 3) map { {(_: Int) * 3} map {(_: Int) + 1} }) assert_=== (List(1, 2, 3) map {_ * 3} map {_ + 1})
  
	
	//Breaking identity law
	import FunctorLaws._
	
	(CSome(0, "ho"): COption[String]) map {_ + "ha"}
                                                  //> res0: org.gwgs.learningscalaz.day04.FunctorLaws.COption[String] = CSome(1,ho
                                                  //| ha)
	//which is not itself CSome(0, "ho")
	(CSome(0, "ho"): COption[String]) map {identity}
                                                  //> res1: org.gwgs.learningscalaz.day04.FunctorLaws.COption[String] = CSome(1,ho
                                                  //| )
                                         
  ////////end of Day 4 Functor Laws //////////
}