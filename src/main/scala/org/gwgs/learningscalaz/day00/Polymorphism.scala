package org.gwgs.learningscalaz.day00

import scala.language.higherKinds
import scala.language.implicitConversions

object Polymorphism {
  
  /**
   * Parametric polymorphism
   */ 
  def head[A](xs: List[A]): A = xs(0)
  
  val numb = head(1 :: 2 :: Nil)
  val string = head("1" :: "2" :: Nil)
  
  
  /**
   * Subtype polymorphism
   * Not flexible since Plus needs to be mixed in at the time of defining the datatype,
   * so it can't work for Int and String
   */
  trait Plus[A] {
    def plus(a2: A): A
  }
  
  def plus[A <: Plus[A]](a1: A, a2: A): A = a1.plus(a2)

  /**
   * Ad-hoc polymorphism
   *  - provide separate function definitions for different types of A
   *  - provide function definition to types (i.e., Int) without access to its source code
   *  - the function definitions can be enabled/disabled in different scopes
   */
  trait Plus1[A] {
    def plus(a1: A, a2: A): A
  }
  
  def plus[A: Plus1](a1: A, a2: A): A = implicitly[Plus1[A]].plus(a1, a2)
  
  /**
   * Monoid - Ad-hoc polymorphism continue
   * Monoid is a Semigroup(append) with a zero method
   */
  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A 
  }
  
  object Monoid {
    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] { 
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }
    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = "" 
    }
  }
  
  /**
   * [A: Monoid] is shorthand syntax for introducing implicit parameters, called Context Bounds.
   * Briefly, a method with a type parameter A that requires an implicit parameter of type M[A].
   * To use this implicit parameter inside method:  val m = implicitly[M[A]]
   * 
   * A more understandable version:
   * def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
   */
  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
  
  /**
   * Method Injection
   */
  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    def |+|(a2: A) = F.mappend(value, a2)
  }
  /**
   * implicit conversion to inject method into closed classes
   */
  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F = implicitly[Monoid[A]]
    val value = a
  }
  
  /**
   * FoldLeft
   */
  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }
  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

  def sum1[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
  
  /**
   * Demo
   */
  def demo = {
    println("============== Polymorphism =============")
    
    //Monoid[Int], Monoid[String] in action
    println("Sum Int: " + sum(List(1, 2, 3, 4)))
    println("Sum String: " + sum(List("1", "2", "3", "4")))
 
    //explicitly pass the monoid in
    val multiMonoid: Monoid[Int] = new Monoid[Int] { 
      def mappend(a: Int, b: Int): Int = a * b
      def mzero: Int = 1
    }
    println("Multiply Int: " + sum(List(1, 2, 3, 4))(multiMonoid))

    //FoldLeft for sum
    println("FoldLeft Int: " + sum1(List(1, 2, 3, 4)))
    println("FoldLeft String: " + sum1(List("a", "b", "c")))
  
    //method injection
    println("Method Injection on Int: " + (3 |+| 6))
    println("Method Injection on String: " + ("W" |+| "y"))
    
    println("")
  }
  
}