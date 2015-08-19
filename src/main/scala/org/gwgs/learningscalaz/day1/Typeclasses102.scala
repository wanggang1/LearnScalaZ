package org.gwgs.learningscalaz.day1

import scalaz._
import Scalaz._

object Typeclasses102 {
  
  case class TrafficLight(name: String)
  
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")
  
  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)
  
  red === yellow
  1 === 2

}