package org.gwgs.learningscalaz.day01

import scalaz._
import Scalaz._

object Typeclasses102Equal {
  
  //Equal
  case class TrafficLight(name: String)
  
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")
  
  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)

}