package org.gwgs.learningscalaz.day03

object worksheet {
  println("Kind - type of types, Tagged type, Monoid")
                                                  //> Kind - type of types, Tagged type, Monoid
  
  import scalaz._
  import Scalaz._
  
  //Tagged type
  import TaggedType._
  
  val mass = KiloGram(21.3)                       //> mass  : scalaz.@@[Double,org.gwgs.learningscalaz.day03.TaggedType.KiloGram] 
                                                  //| = 21.3
  2 * Tag.unwrap(mass)                            //> res0: Double = 42.6
  
  energyR(mass)                                   //> res1: scalaz.@@[Double,org.gwgs.learningscalaz.day03.TaggedType.JoulePerKilo
                                                  //| Gram] = 1.91434853070942157E18
  
  
  //Monoid
  //A monoid is when you have an associative binary function
  //and a value which acts as an identity with respect to that function
  
  List(1, 2, 3) mappend List(4, 5)                //> res2: List[Int] = List(1, 2, 3, 4, 5)
  "one" mappend "two"                             //> res3: String = onetwo
  //idiomatic Scalaz way
  List(1,2,3) |+| List(4,5)                       //> res4: List[Int] = List(1, 2, 3, 4, 5)
  "one" |+| "two"                                 //> res5: String = onetwo
  
  //zero elements(identity) for mappend
  Monoid[List[Int]].zero                          //> res6: List[Int] = List()
  Monoid[String].zero                             //> res7: String = ""
  Monoid[Int @@ Tags.Multiplication].zero         //> res8: scalaz.@@[Int,scalaz.Tags.Multiplication] = 1
  //for addition
  Monoid[Int].zero                                //> res9: Int = 0
  
}