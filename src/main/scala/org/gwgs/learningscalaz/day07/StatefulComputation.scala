package org.gwgs.learningscalaz.day07

object StatefulComputation {
  import scalaz._
  import Scalaz._
  
  type Stack = List[Int]
  
  def pop = State[List[Int], Int] {
    case Nil => 
      println("pop from Nil")
      (List.empty[Int], -1)
    case x :: xs => 
      println(s"pop: $x")
      (xs, x)
  }
  
  def push(a: Int)= State[List[Int], Unit] {
    case Nil =>
      println("push to Nil")
      (a :: Nil, ())
    case xs =>
      println(s"push: $a")
      (a :: xs, ())
  }
  
  def show(a: Int)= State[List[Int], Unit] {
    case xs => 
      println(s"Show Stateful: $xs and $a")
      (xs, ())
  }
  
  def stackManip(stack: Stack): State[List[Int], Int] = for {
    _ <- push(3)
    a <- pop
    b <- pop
  } yield (b)
  
  
  def demo = {
    println("============== Stateful ===============")
    
    for {
      x <- stackManip(List(5, 8, 2, 1))
      _ <- show(x)
    } yield x
    
    println("")
  }
  
}