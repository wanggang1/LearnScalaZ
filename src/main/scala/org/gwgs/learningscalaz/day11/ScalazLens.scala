package org.gwgs.learningscalaz.day11

object ScalazLens {
  import scalaz._
  import Scalaz._
  import Lens._

  case class Point(x: Double, y: Double)
  case class Color(r: Byte, g: Byte, b: Byte)

  case class Turtle(position: Point, heading: Double, color: Color) {

    //To update the child data structure, we need to nest copy call.
    def forward(dist: Double): Turtle =
      copy(position =
        position.copy(
          x = position.x + dist * math.cos(heading),
          y = position.y + dist * math.sin(heading)
        ))

  }


  // Use Lens to get rid of unnecessary copy calls

  val turtlePosition: Lens[Turtle,Point] = lensu((a, value) => a.copy(position = value), _.position)
  val pointX: Lens[Point, Double] = lensu((a, value) => a.copy(x = value), _.x)
  val pointY: Point @> Double = lensu((a, value) => a.copy(y = value), _.y)

  val turtleX: Lens[Turtle, Double] = turtlePosition >=> pointX
  val turtleY: Turtle @> Double = turtlePosition >=> pointY

  def move(t: Turtle, dist: Double): Turtle = {
    val deltaX = dist * math.cos(t.heading)
    val deltaY = dist * math.sin(t.heading)
    val t1 = turtleX.set(t, turtleX.get(t) + deltaX)
    turtleY.set(t1, turtleY.get(t) + deltaY)
  }

  def demo: Unit = {
    val t0 = Turtle(Point(2.0, 3.0), 0.0, Color(255.toByte, 255.toByte, 255.toByte))
    val t1 = turtleX.set(t0, 5.0)
    println(s"turtleX.get(t0) = ${turtleX.get(t0)}")
    println(s"new turtle, $t1")

    println(move(t0, 10))
  }

}

