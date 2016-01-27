package org.gwgs.learningscalaz.day02

object worksheet {
  println("Functor")                              //> Functor
  
	trait ABC[A] {
	  val value: A
	}
	
	trait XYZ[A] { self: ABC[A] =>
	  def xyz(a: A): Boolean = a == value
	}
	
	import scalaz._
  import Scalaz._
  
  //Funtcor typeclass
  (1, 2, 3) map {_ + 1}                           //> res0: (Int, Int, Int) = (1,2,4)
  
  val doubleList = Functor[List].lift {(_: Int) * 2}
                                                  //> doubleList  : List[Int] => List[Int] = <function1>
  doubleList(List(3, 5))                          //> res1: List[Int] = List(6, 10)
  
  List(1, 2, 3) >| "x"                            //> res2: List[String] = List(x, x, x)
  
  List(1, 2, 3) as "x"                            //> res3: List[String] = List(x, x, x)
  
  List(1, 2, 3).fpair                             //> res4: List[(Int, Int)] = List((1,1), (2,2), (3,3))
  
  List(1, 2, 3).strengthL("x")                    //> res5: List[(String, Int)] = List((x,1), (x,2), (x,3))
  
  List(1, 2, 3).strengthR("x")                    //> res6: List[(Int, String)] = List((1,x), (2,x), (3,x))
  
  List(1, 2, 3).void                              //> res7: List[Unit] = List((), (), ())
  
  
  //Applicative typeclass
  1.point[List]                                   //> res8: List[Int] = List(1)
  2.point[Option]                                 //> res9: Option[Int] = Some(2)

	//Apply function
	1.some                                    //> res10: Option[Int] = Some(1)
  9.some <*> {(_: Int) + 3}.some                  //> res11: Option[Int] = Some(12)
  1.some <* 2.some                                //> res12: Option[Int] = Some(1)
  1.some *> 2.some                                //> res13: Option[Int] = Some(2)
  
  ^(3.some, 5.some) {_ + _}                       //> res14: Option[Int] = Some(8)
  
  ^(3.some, none[Int]) {_ + _}                    //> res15: Option[Int] = None
  
  (3.some |@| 5.some) {_ + _}                     //> res16: Option[Int] = Some(8)
  
  //List as Apply
  List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x)
                                                  //> res17: List[Int] = List(0, 0, 0, 101, 102, 103, 1, 4, 9)
 	(List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
                                                  //> res18: List[String] = List(ha?, ha!, ha., heh?, heh!, heh., hmm?, hmm!, hmm.
                                                  //| )
  val concatInOption = Apply[Option].lift2((_: Int) :: (_: List[Int]))
                                                  //> concatInOption  : (Option[Int], Option[List[Int]]) => Option[List[Int]] = <
                                                  //| function2>
  concatInOption(3.some, List(4, 5).some)         //> res19: Option[List[Int]] = Some(List(3, 4, 5))
  concatInOption(none[Int], List(4, 5).some)      //> res20: Option[List[Int]] = None
  
  import ApplicativeOps._
  sequenceA(List(1.some, 2.some))                 //> res21: Option[List[Int]] = Some(List(1, 2))
  sequenceA(List(1.some, none, 2.some))           //> res22: Option[List[Int]] = None
  
}