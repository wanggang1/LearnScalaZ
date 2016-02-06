package org.gwgs.learningscalaz.day07

object worksheet {
  println("Day 7, Applicative Builder")           //> Day 7, Applicative Builder

  import scalaz._
  import Scalaz._
   
  //State
  import StatefulComputation._
  
  stackManip(List(5, 8, 2, 1))                    //> res0: scalaz.Id.Id[(org.gwgs.learningscalaz.day07.StatefulComputation.Stack,
                                                  //|  Int)] = (List(8, 2, 1),5)
  
  //Either
  1.right[String]                                 //> res1: scalaz.\/[String,Int] = \/-(1)
  "error".left[Int]                               //> res2: scalaz.\/[String,Int] = -\/(error)
  
	for {
		e1 <- "event 1 ok".right
		e2 <- "event 2 failed!".left[String]
		e3 <- "event 3 failed!".left[String]
	} yield (e1 |+| e2 |+| e3)                //> res3: scalaz.\/[String,String] = -\/(event 2 failed!)
	
	for {
		e1 <- 1.right[String]
		e2 <- 2.right[String]
		e3 <- 3.right[String]
	} yield (e1 |+| e2 |+| e3)                //> res4: scalaz.\/[String,Int] = \/-(6)
	
	"event 1 ok".right.isRight                //> res5: Boolean = true
	"event 1 ok".right.isLeft                 //> res6: Boolean = false
	
	1.wrapNel                                 //> res7: scalaz.NonEmptyList[Int] = NonEmpty[1]
	
}