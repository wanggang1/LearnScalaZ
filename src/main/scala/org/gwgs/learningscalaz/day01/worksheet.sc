package org.gwgs.learningscalaz.day01

object worksheet {
  println("Typeclasses")                          //> Typeclasses
  
  import scalaz._
  import Scalaz._
  
	//Order
  1 > 2.0                                         //> res0: Boolean(false) = false
  
  1.0 gt 2.0                                      //> res1: Boolean = false
  
  1 ?|? 2                                         //> res2: scalaz.Ordering = LT
  
  1.0 max 2.0                                     //> res3: Double = 2.0
	  
	  
  //Show
  println("Show: " + 1.show)                      //> Show: 1
  println("Shows: " + 1.shows)                    //> Shows: 1
  1.println                                       //> 1
  
  
  //Enum
  'a' |-> 'e'                                     //> res4: List[Char] = List(a, b, c, d, e)
  'a' |=> 'e'                                     //> res5: scalaz.EphemeralStream[Char] = scalaz.EphemeralStreamFunctions$$anon$4
                                                  //| @4404e0d3
   
  //Equal
	1 === 1                                   //> res6: Boolean = true
	1 =/= 2                                   //> res7: Boolean = true

	//Customized Equal
  import Typeclasses102Equal._
  
  red =/= yellow                                  //> res8: Boolean = true
  green === green                                 //> res9: Boolean = true

  
  //Truthy - Customized typeclass
  import YesNoTypeclass.CanTruthy
  import YesNoTypeclass.ToCanIsTruthyOps._
  
  implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
     case 0 => false
     case _ => true
   })                                             //> intCanTruthy  : org.gwgs.learningscalaz.day01.YesNoTypeclass.CanTruthy[Int] 
                                                  //| = org.gwgs.learningscalaz.day01.YesNoTypeclass$CanTruthy$$anon$1@3f255c01
  
  10.truthy                                       //> res10: Boolean = true
  0.truthy                                        //> res11: Boolean = false
 	 
 	implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
     case Nil => false
     case _   => true
   })                                             //> listCanTruthy: [A]=> org.gwgs.learningscalaz.day01.YesNoTypeclass.CanTruthy[
                                                  //| List[A]]
   
  implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)
                                                  //> nilCanTruthy  : org.gwgs.learningscalaz.day01.YesNoTypeclass.CanTruthy[colle
                                                  //| ction.immutable.Nil.type] = org.gwgs.learningscalaz.day01.YesNoTypeclass$Can
                                                  //| Truthy$$anon$1@3f4fceb7
  
  List("foo", "bar").truthy                       //> res12: Boolean = true
  Nil.truthy                                      //> res13: Boolean = false
    
  truthyIf (Nil) {"YEAH!"} {"NO!"}                //> res14: Any = NO!
  
  truthyIf (2 :: 3 :: 4 :: Nil) {"YEAH!"} {"NO!"} //> res15: Any = YEAH!
  
  implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)
                                                  //> booleanCanTruthy  : org.gwgs.learningscalaz.day01.YesNoTypeclass.CanTruthy[
                                                  //| Boolean] = org.gwgs.learningscalaz.day01.YesNoTypeclass$CanTruthy$$anon$1@2
                                                  //| 7f074cb
  
  false.truthy                                    //> res16: Boolean = false
  
    
  truthyIf (true) {"YEAH!"} {"NO!"}               //> res17: Any = YEAH!


}