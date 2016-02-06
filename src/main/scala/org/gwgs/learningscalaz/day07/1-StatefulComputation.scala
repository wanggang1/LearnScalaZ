package org.gwgs.learningscalaz.day07

/**
 * State monad encapsulate a function that returns a tuple
 */
object StatefulComputation {
  import scalaz._
  import Scalaz._
  
  type Stack = List[Int]

  def pop = State[Stack, Int] {
    case x :: xs => (xs, x)
  }
  
  def push(a: Int) = State[Stack, Unit] {
    case xs => (a :: xs, ())
  }

  /**  implement pop and push with functions on StateFunctions
   * 
   * val pop: State[Stack, Int] = for {
         s <- get[Stack]
         val (x :: xs) = s
         _ <- put(xs)
       } yield x
       
     def push(x: Int): State[Stack, Unit] = for {
         xs <- get[Stack]
         r <- put(x :: xs)
       } yield r
   */
  
  /**
   * How the existing state being passed to stackManip???
   * see scalaz.StateFunctions
   */
  def stackManip: State[Stack, Int] = for {
    _ <- push(3)
    a <- pop
    b <- pop
  } yield (b)
  
  /**
   * get: State[S,S].  for comprehension on State getting the 2nd value of tuple2
   */
  def stackyStack: State[Stack, Unit] = for {
     stackNow <- get
     r <- if (stackNow === List(1, 2, 3)) put(List(8, 3, 1))
          else put(List(9, 2, 1))
   } yield r
  
  
  def demo = {
    println("============== Stateful ===============")
    
    val t2 = stackManip(List(5, 8, 2, 1))
    println(s"State: $t2")
    
    val tuple2 = stackyStack(List(1, 2, 3))
    println(s"Current State: $tuple2")
    
    println("")
  }
  
}