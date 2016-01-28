package org.gwgs.learningscalaz.day02

object worksheet {

  //demo of self type, not related to Functor
	trait ABC[A] {
	  def value: A
	}
	
	trait XYZ[A] { self: ABC[A] =>
	  def xyz(a: A): Boolean = a == value
	}
	
	class D(v: Int) extends ABC[Int] with XYZ[Int] {
		def value = v
	}
	
	val d = new D(3)                          //> d  : org.gwgs.learningscalaz.day02.worksheet.D = org.gwgs.learningscalaz.day
                                                  //| 02.worksheet$D@cac736f
	d.xyz(1)                                  //> res0: Boolean = false
	d.xyz(3)                                  //> res1: Boolean = true
	///////////////////////////////////////////////////
	
	import scalaz._
  import Scalaz._
  
  //scalaz Funtcor typeclass
	(1, 2, 3) map {_ + 1}                     //> res2: (Int, Int, Int) = (1,2,4)
	
	val doubleList = Functor[List].lift {(_: Int) * 2}
                                                  //> doubleList  : List[Int] => List[Int] = <function1>
	
	doubleList(List(3, 5))                    //> res3: List[Int] = List(6, 10)
	
	List(1, 2, 3) >| "x"                      //> res4: List[String] = List(x, x, x)
	
	List(1, 2, 3) as "x"                      //> res5: List[String] = List(x, x, x)
	
	List(1, 2, 3).fpair                       //> res6: List[(Int, Int)] = List((1,1), (2,2), (3,3))
	
	List(1, 2, 3).strengthL("x")              //> res7: List[(String, Int)] = List((x,1), (x,2), (x,3))
	
	List(1, 2, 3).strengthR("x")              //> res8: List[(Int, String)] = List((1,x), (2,x), (3,x))
	
	List(1, 2, 3).void                        //> res9: List[Unit] = List((), (), ())
  
  
  //scalaz Applicative typeclass
  1.point[List]                                   //> res10: List[Int] = List(1)
  2.point[Option]                                 //> res11: Option[Int] = Some(2)

	//Apply function
	1.some                                    //> res12: Option[Int] = Some(1)
  9.some <*> {(_: Int) + 3}.some                  //> res13: Option[Int] = Some(12)
  1.some <* 2.some                                //> res14: Option[Int] = Some(1)
  1.some *> 2.some                                //> res15: Option[Int] = Some(2)
  
  ^(3.some, 5.some) {_ + _}                       //> res16: Option[Int] = Some(8)
  
  ^(3.some, none[Int]) {_ + _}                    //> res17: Option[Int] = None
  
  (3.some |@| 5.some) {_ + _}                     //> res18: Option[Int] = Some(8)
  
  //List as Apply
  List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x)
                                                  //> res19: List[Int] = List(0, 0, 0, 101, 102, 103, 1, 4, 9)
 	(List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
                                                  //> res20: List[String] = List(ha?, ha!, ha., heh?, heh!, heh., hmm?, hmm!, hmm
                                                  //| .)
  
}