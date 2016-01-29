package org.gwgs.learningscalaz.day05

object worksheet {
  println("Day 5 - Monad")                        //> Day 5 - Monad
  
  import scalaz._
  import Scalaz._
  
  3.some flatMap { x => (x + 1).some }            //> res0: Option[Int] = Some(4)
  (none: Option[Int]) flatMap { x => (x + 1).some }
                                                  //> res1: Option[Int] = None
  Monad[Option].point("WHAT")                     //> res2: Option[String] = Some(WHAT)
  9.some flatMap { x => Monad[Option].point(x * 10) }
                                                  //> res3: Option[Int] = Some(90)
  (none: Option[Int]) flatMap { x => Monad[Option].point(x * 10) }
                                                  //> res4: Option[Int] = None
  (none: Option[Int]) >> 3.some                   //> res5: Option[Int] = None
  3.some >> (none: Option[Int])                   //> res6: Option[Int] = None
  4.some >> 3.some                                //> res7: Option[Int] = Some(3)
  
  for {
  	(x :: xs) <- "".toList.some
  } yield x                                       //> res8: Option[Char] = None
                                                  
  
  import BalancedPoolWalk._
  
  Pole(0, 0).landRight(1) flatMap {_.landLeft(2)} //> res9: Option[org.gwgs.learningscalaz.day05.BalancedPoolWalk.Pole] = Some(Pol
                                                  //| e(2,1))
	(none: Option[Pole]) flatMap {_.landLeft(2)}
                                                  //> res10: Option[org.gwgs.learningscalaz.day05.BalancedPoolWalk.Pole] = None
	Monad[Option].point(Pole(0, 0)) >>= {_.landRight(2)} >>= {_.landLeft(2)} >>= {_.landRight(2)}
                                                  //> res11: Option[org.gwgs.learningscalaz.day05.BalancedPoolWalk.Pole] = Some(Po
                                                  //| le(2,4))
	Monad[Option].point(Pole(0, 0)) >>= {_.landLeft(1)} >>= {_.landRight(4)} >>= {_.landLeft(-1)} >>= {_.landRight(-2)}
                                                  //> res12: Option[org.gwgs.learningscalaz.day05.BalancedPoolWalk.Pole] = None
  Monad[Option].point(Pole(0, 0)) >>= {_.landLeft(1)} >>= {_.banana} >>= {_.landLeft(1)}
                                                  //> res13: Option[org.gwgs.learningscalaz.day05.BalancedPoolWalk.Pole] = None
                                                  
  //using List as Applicative
  ^(List(1, 2, 3), List(10, 100, 100)) {_ * _}    //> res14: List[Int] = List(10, 100, 100, 20, 200, 200, 30, 300, 300)
  (List(1, 2, 3) |@| List(10, 100, 100)) {_ * _}  //> res15: List[Int] = List(10, 100, 100, 20, 200, 200, 30, 300, 300)
  
  for {
  	x1 <- List(1, 2, 3)
  	x2 <- List(10, 100, 100)
  } yield x1 * x2                                 //> res16: List[Int] = List(10, 100, 100, 20, 200, 200, 30, 300, 300)
  
  
 //Knight move on Chess board
 import ChessBoard._
 KnightPos(6, 2).move                             //> res17: List[org.gwgs.learningscalaz.day05.ChessBoard.KnightPos] = List(Knig
                                                  //| htPos(8,1), KnightPos(8,3), KnightPos(4,1), KnightPos(4,3), KnightPos(7,4),
                                                  //|  KnightPos(5,4))
 KnightPos(8, 1).move                             //> res18: List[org.gwgs.learningscalaz.day05.ChessBoard.KnightPos] = List(Knig
                                                  //| htPos(6,2), KnightPos(7,3))
 KnightPos(6, 2) canReachIn3 KnightPos(6, 1)      //> res19: Boolean = true
 KnightPos(6, 2) canReachIn3 KnightPos(7, 3)      //> res20: Boolean = false
 

}