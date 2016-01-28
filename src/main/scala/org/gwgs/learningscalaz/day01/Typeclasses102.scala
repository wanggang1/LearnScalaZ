package org.gwgs.learningscalaz.day01

import scalaz._
import Scalaz._

object Typeclasses102Equal {
  
  //Equal
  case class TrafficLight(name: String)
  
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")
  
  /**
   * Using scalaz Equal type class
   */
  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)
  
  
  def demo = {
    println("============== Equal ====================")
    
    println("TrafficLight red =/= yellow -> " + (red =/= yellow))
    println("TrafficLight green === green -> " + (green === green))
    
    println("")   
  }

}
