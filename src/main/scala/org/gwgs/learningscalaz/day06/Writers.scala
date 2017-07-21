package org.gwgs.learningscalaz.day06

object Writers {
  import scalaz._
  import Scalaz._
  
  def isBigGang(x: Int): (Boolean, String) =
    (x > 9, "Compared gang size to 9.")

  /*
   * To attach a monoid to a value, we just need to put them together
   * in a tuple. The Writer w a type is just a newtype wrapper for this.
   */
  implicit class PairOps[A, B: Monoid](pair: (A, B)) {
    def applyLog[C](f: A => (C, B)): (C, B) = {
      val (x, log) = pair
      val (y, newlog) = f(x)
      (y, log |+| newlog)
    }
  }

  /////// Using scalaz type Writer[+W, +A]
  def logNumber(x: Int): Writer[List[String], Int] =
    x.set(List("Got number: " + x.shows))

  def multWithLog: Writer[List[String], Int] =
    for {
      a <- 3.set(List("Got number: 3"))
      b <- 5.set(List("Got number: 5"))
    } yield a * b

  //If the values need to be used again like above, use set,
  //otherwise, just use tell
  def gcd(a: Int, b: Int): Writer[Vector[String], Int] =
    if (b == 0)
      for {
        _ <- Vector("Finished with " + a.shows).tell
      } yield a
    else
      Vector(a.shows + " mod " + b.shows + " = " + (a % b).shows).tell >>= {
        _ => gcd(b, a % b)
      }


  def demo: Unit = {
    val tLog = (3, "Smallish gang.") applyLog isBigGang
    println(tLog)

    val wLog = logNumber(3)
    println(wLog)

    val mLog = multWithLog
    mLog.productIterator foreach println

    val gcdLog1 = gcd(12, 0)
    println(gcdLog1)

    val gcdLog2 = gcd(45, 63)
    println(gcdLog2)
  }
}