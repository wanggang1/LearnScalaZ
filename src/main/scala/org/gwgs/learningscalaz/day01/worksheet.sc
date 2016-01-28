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
                                                  //| @3e694b3f
   
  //Equal
	1 === 1                                   //> res6: Boolean = true
	1 =/= 2                                   //> res7: Boolean = true

}