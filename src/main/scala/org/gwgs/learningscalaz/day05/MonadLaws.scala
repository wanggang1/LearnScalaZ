package org.gwgs.learningscalaz.day05

object MonadLaws {
  import scalaz._
  import Scalaz._
  
  /** 1. Left Identity
   * The first monad law states that if we take a value, put it in a default context with pure
   * and then feed it to a function by using >>=, it’s the same as just taking the value and 
   * applying the function to it.
   */
  //(Monad[F].point(x) flatMap {f}) assert_=== f(x)
  (Monad[Option].point(3) >>= { x => (x + 100000).some }) assert_=== 3 |> { x => (x + 100000).some }
  
  /** 2 Right Identity
   * The second law states that if we have a monadic value and we use >>= to feed it to pure,
   * the result is our original monadic value.
   */
  // (m flatMap {Monad[F].point(_)}) assert_=== m
  ("move on up".some >>= {Monad[Option].point(_)}) assert_=== "move on up".some
  
  /** 3. Associativity
   * The final monad law says that when we have a chain of monadic function applications with
   * flatMap, it shouldn’t matter how they’re nested.
   */
  // (m flatMap f) flatMap g assert_=== m flatMap { x => f(x) flatMap {g} }
  import BalancedPoolWalk.Pole
  val p1 = Monad[Option].point(Pole(0, 0)) >>= {_.landRight(2)} >>= {_.landLeft(2)} >>= {_.landRight(2)}
  
  val p2 = Monad[Option].point(Pole(0, 0)) >>= { x =>
             x.landRight(2) >>= { y =>
               y.landLeft(2) >>= { z =>
                 z.landRight(2)
               }
             }
           }
  
}