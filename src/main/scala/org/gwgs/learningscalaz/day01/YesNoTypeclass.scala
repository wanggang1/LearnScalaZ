package org.gwgs.learningscalaz.day01

//import scalaz._
//import Scalaz._

object YesNoTypeclass {
  
  trait CanTruthy[A] { self =>
    /** @return true, if `a` is truthy. */
    def truthys(a: A): Boolean
  }
  
  object CanTruthy {
    def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
    
    def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
      def truthys(a: A): Boolean = f(a)
    }
  }
  
  trait CanTruthyOps[A] {
    def self: A
    implicit def F: CanTruthy[A]
    final def truthy: Boolean = F.truthys(self)
  }
  
  object ToCanIsTruthyOps {
    import scala.language.implicitConversions
    
    implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
      new CanTruthyOps[A] {
        def self = v
        implicit def F: CanTruthy[A] = ev
      }
    
    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
      if (cond.truthy) ifyes
      else ifno
  }
  

  def demo = {
    println("============== Truthy - Customized typeclass =")
    
    //Truthy - Customized typeclass
    import ToCanIsTruthyOps._
    
    implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
       case 0 => false
       case _ => true
     })
    
     implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
       case Nil => false
       case _   => true
     })
     
    implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)
    
    implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)
    
    println("add truthy to Int - 10.truthy: " + 10.truthy)
    println("add truthy to Int - 0.truthy: " + 0.truthy)

    println("add truthy to List - List('foo', 'bar').truthy: " + List("foo", "bar").truthy)
    println("add truthy to List - Nil.truthy: " + Nil.truthy)
    
    println("add truthy to List - true.truthy: " + true.truthy)
    println("add truthy to List - false.truthy: " + false.truthy)
      
    println("truthyIf: " + truthyIf (Nil) {"YEAH!"} {"NO!"})
    
    println("truthyIf: " + truthyIf (2 :: 3 :: 4 :: Nil) {"YEAH!"} {"NO!"})
      
    println("truthyIf: " + truthyIf (true) {"YEAH!"} {"NO!"})
    
    println("")
  }

}