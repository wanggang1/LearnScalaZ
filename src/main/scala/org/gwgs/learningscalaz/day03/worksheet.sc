package org.gwgs.learningscalaz.day03

object worksheet {
  println("Monoid")                               //> Monoid
  
  import scalaz.{@@, Monoid, Scalaz, Tags}
  import Scalaz._
  
  //Monoid
  //A monoid is when you have an associative binary function
  //and a value which acts as an identity with respect to that function
  
  List(1, 2, 3) mappend List(4, 5)                //> res0: List[Int] = List(1, 2, 3, 4, 5)
  "one" mappend "two"                             //> res1: String = onetwo
  //idiomatic Scalaz way
  List(1,2,3) |+| List(4,5)                       //> res2: List[Int] = List(1, 2, 3, 4, 5)
  "one" |+| "two"                                 //> res3: String = onetwo
  
  //zero elements(identity) for mappend
  Monoid[List[Int]].zero                          //> res4: List[Int] = List()
  Monoid[String].zero                             //> res5: String = ""
  Monoid[Int @@ Tags.Multiplication].zero         //> res6: scalaz.@@[Int,scalaz.Tags.Multiplication] = 1
  //for addition
  Monoid[Int].zero                                //> res7: Int = 0
  
  //multiply numbers using |+|
  Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero
                                                  //> res8: scalaz.@@[Int,scalaz.Tags.Multiplication] = 10
  //add numbers using |+|
  10 |+| Monoid[Int].zero                         //> res9: Int = 10
  
  //or(||) operation on Boolean using |+|, flase is the zero value
  Tags.Disjunction(true) |+| Tags.Disjunction(false)
                                                  //> res10: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = true
  Monoid[Boolean @@ Tags.Disjunction].zero |+| Tags.Disjunction(true)
                                                  //> res11: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = true
  Monoid[Boolean @@ Tags.Disjunction].zero |+| Monoid[Boolean @@ Tags.Disjunction].zero
                                                  //> res12: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = false
  
  //and(&&) operation on Boolean using |+|, true is the zero value
  Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(true)
                                                  //> res13: scalaz.@@[Boolean,scalaz.Tags.Conjunction] = true
  Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(false)
                                                  //> res14: scalaz.@@[Boolean,scalaz.Tags.Conjunction] = false
  
}