package org.gwgs.learningscalaz.day07


object ScalazEither {
  
  /**
   * The Either type in Scala standard library is not a monad on its own, which means it
   * does not implement flatMap method with or without Scalaz
   */
  val left: Either[String, Int] = Left[String, Int]("boom")
  val right : Either[String, Int] = Right[String, Int](1)
  //Not compile: 
  //  left flatMap { x => Right[String, Int](x + 1) }
  //  right flatMap { x => Right[String, Int](x + 1) }
  //have to convert to the Either.RightProjection, left.right or right.right
  val rightResult: Either[String, Int] = right.right flatMap { x => Right[String, Int](x + 1)}
  val leftResult: Either[String, Int] = left.right flatMap { x => Right[String, Int](x + 1)}
  
  /**
   * Scalaz
   */
  import scalaz._
  import Scalaz._
  
  val zRight: \/[String, Int] = 1.right[String]
  
  val zLeft: \/[String, Int] = "error".left[Int]
  val zLeftResult = zLeft >>= { x => (x + 1).right }
  
  val event: \/[String, String] = "event 1 ok".right[String]
  val event2: \/[String, String] = event >>= {x => (x + "!").right}
  
  //For right value, getOrElse or its alias |
  val eventValue: String = event getOrElse "bad event"
  val event2Value: String = event2 | "bad event2"
  
  //For left value, use swap method or it’s symbolic alias unary_~:
  val leftValue = ~zLeft | "general error message"
  
  //Chaining
  val retry1 = "event 1 ok".right ||| "retry event 1 ok".right
  val retry2 = "event 1 failed!".left ||| "retry event 1 ok".right
  
  def demo = {
    println("============== Scalaz Either ===============")
    
    println(s"rightResult: $rightResult")
    println(s"leftResult: $leftResult")
    println(s"zLeftResult: $zLeftResult")
    println(s"event: $event")
    println(s"event2: $event2")
    println(s"eventValue: $eventValue")
    println(s"event2Value: $event2Value")
    println(s"leftValue: $leftValue")
    println(s"retry1: $retry1")
    println(s"retry2: $retry2")
    
    println("")
  }
}