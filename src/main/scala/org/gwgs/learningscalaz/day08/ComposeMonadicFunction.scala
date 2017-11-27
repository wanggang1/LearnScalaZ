package org.gwgs.learningscalaz.day08

import scalaz._
import Scalaz._

object ComposeMonadicFunction {

  val f = Kleisli { (x: Int) => (x + 1).some }

  val g = Kleisli { (x: Int) => (x * 100).some }

  val x = 4.some >>= (f <=< g) //f compose g

  val y = 4.some >>= (f >=> g) //f andThen g


  def demo: Unit = {
    println(x)
    println(y)
  }

}
