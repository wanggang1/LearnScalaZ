package org.gwgs.learningscalaz.day02

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

import scalaz._
import Scalaz._

object ApplicativeBuilder {
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.language.postfixOps

  def demo: Unit = {
    val fSum: Future[Int] = (Future(1) |@| Future(2) |@| Future(3)) { (x,y,z) => x + y + z }
    val sum = Await.result(fSum, 5 seconds)
    println(s"sum in Future: $sum")

    //why has to use .some or the type can't be inferred in the function ???
    val hello = ("Hello, ".some |@| Some("World") |@| Some("!")) {(x,y,z) => x + y + z}
    hello map {println}

    val helloNone: Option[String] = ("Hello, ".some |@| Some("World") |@| None) {(x,y,z:String) => x + y + z}
    helloNone map {println}
  }
}
