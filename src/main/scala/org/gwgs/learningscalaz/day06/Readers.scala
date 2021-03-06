package org.gwgs.learningscalaz.day06

object Readers {
  import scalaz._
  import Scalaz._
  
  val addStuff: Int => Int =
    for {
      a <- (_: Int) * 2
      b <- (_: Int) + 10
    } yield a + b

  def demo: Unit = {

    println(addStuff(3))

    /*
     * Reader encapsulates a function A => B
     *
     * Use Reader for dependency injection:
     * for {
     *   domain <- Reader(dependency => DomainObject)
     * } yield domain
     */
    val triple: Reader[Int, Int] = Reader((i: Int) => i * 3)
    val result1: Id.Id[Int] = triple(4)
    for {
      result <- triple(3)
    } yield println((result, result1))

  }
}