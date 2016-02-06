package org.gwgs.learningscalaz.day07

object ScalazValidation {
  
  import scalaz._
  import Scalaz._
  
  /**
   * Validation[+E, +A]
   * 
   * ValidationOps introduces success[X], successNel[X], failure[X], and failureNel[X] methods
   * to all data types:
   */
  val event1 = "event 1 ok".success[String]
  val failure2 = "event 2 failed!".failure[String]
  
  /**
   * Validation is not a monad, but it’s an applicative functor. Instead of chaining
   * the result from first event to the next, Validation validates all events.  But
   * the error messages are meshed together into one string inside Failure.....
   */
  val allFailures = (event1 |@|
                     failure2 |@|
                     "event 3 failed!".failure[String]) {_ + _ + _}
  
  /**
   * NonEmptyList is a wrapper trait for plain List that’s guaranteed to be non-empty.
   * Since there’s at least one item in the list, head always works. IdOps adds wrapNel
   * to all data types to create a Nel, 1.wrapNel.
   */
  val event1Nel = "event 1 ok".successNel[String]
  val failure2Nel = "event 2 failed!".failureNel[String]
  val allFailuresNel = (event1Nel |@|
                        failure2Nel |@|
                        "event 3 failed!".failureNel[String]) {_ + _ + _}
  val allEventsNel = (event1Nel |@|
                      "event 2 ok".successNel[String] |@|
                      "event 3 ok".successNel[String]) {_ + _ + _}
  
  def demo = {
    println("============== Scalaz Either ===============")
    
    println(s"event1: $event1")
    println(s"failure1: $failure2")
    println(s"allFailures: $allFailures")
    println(s"event1Nel: $event1Nel")
    println(s"failure1Nel: $failure2Nel")
    println(s"event1 == event1Nel: " + (event1 == event1Nel))
    println(s"allFailuresNel: $allFailuresNel")
    println(s"allEventsNel: $allEventsNel")

    println("")
  }
  
}