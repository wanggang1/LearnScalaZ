package org.gwgs.learningscalaz.day0

object Polymorphism {
  
  /*
   * Parametric polymorphism
   */ 
  val numb = head(1 :: 2 :: Nil)
  val string = head("1" :: "2" :: Nil)
  
  def head[A](xs: List[A]): A = xs(0)
  
  
  /*
   * Subtype polymorphism
   * Not flexible since Plus needs to be mixed in at the time of defining the datatype,
   * so it can't work for Int and String
   */
  trait Plus[A] {
    def plus(a2: A): A
  }
  
  def plus[A <: Plus[A]](a1: A, a2: A): A = a1.plus(a2)
  
  
  /*
   * Ad-hoc polymorphism
   *  - provide seperate function definitions for different types of A
   *  - provide function definition to types (i.e., Int) without access to its source code
   *  - the function definitions can be enabled/disabled in different scopes
   */
  trait Plus1[A] {
    def plus(a1: A, a2: A): A
  }
  
  def plus[A: Plus1](a1: A, a2: A): A = implicitly[Plus1[A]].plus(a1, a2)
  
  
  /*
   * Monoid - Ad-hoc polymorphism continue
   */
  sum(List(1, 2, 3, 4))
  sum(List("1", "2", "3", "4"))
  
  //explicitlt pass the monoid in
  val multiMonoid: Monoid[Int] = new Monoid[Int] { 
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }
  sum(List(1, 2, 3, 4))(multiMonoid)
  
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
  
  /*
   * What is [A: Monoid] here???
   * 
   * A more understandable version:
   * def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
   */
  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

}