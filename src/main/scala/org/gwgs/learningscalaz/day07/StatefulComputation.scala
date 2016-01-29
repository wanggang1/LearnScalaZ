package org.gwgs.learningscalaz.day07

object StatefulComputation {
  import scalaz._
  import Scalaz._
  
  type Stack = List[Int]
  
  def pop = State[List[Int], Int] {
    case x :: xs => (xs, x)
  }
  
  def push(a: Int)= State[List[Int], Unit] {
    case xs => (a :: xs, ())
  }
  
  def stackManip(stack: Stack): State[List[Int], Int] = for {
    _ <- push(3)
    a <- pop
    b <- pop
  } yield (b)
  
}