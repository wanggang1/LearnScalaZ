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
   *  - There must be an implicit Monoid[E] exists for errors to concatenate?
   */
  val allFailures: Validation[String, String] =
    (event1 |@|
     failure2 |@|
     "event 3 failed!".failure[String]) {_ + _ + _}
  val allFailures2: Validation[String, List[String]] =
    (event1 |@|
    failure2 |@|
    "event 3 failed!".failure[String]) {List(_, _, _)}

  //because of the Monoid[E]
  allFailures == allFailures2
  
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

  //user defined types
  case class Job(name: String, numb: Int)

  val jobNel1: ValidationNel[String, Job] = Job("A", 1).successNel[String]
  val jobNel2: ValidationNel[String, Job] = Job("B", 2).successNel[String]
  val jobNel3: ValidationNel[String, Job] = Job("C", 3).successNel[String]

  val jobFailNel2: ValidationNel[String, Job] = "job 2 failed".failureNel[Job]
  val jobFailNel3: ValidationNel[String, Job] = "job 3 failed".failureNel[Job]

  val allJobs: Validation[NonEmptyList[String],List[Job]] = (jobNel1 |@| jobNel2 |@| jobNel3) {List(_, _, _)}
  val jobsFailed: Validation[NonEmptyList[String],List[Job]] = (jobNel1 |@| jobFailNel2 |@| jobFailNel3) {List(_, _, _)}
  
  def demo = {
    println("============== Scalaz Either ===============")
    
    println(s"event1: $event1")
    println(s"failure1: $failure2")
    println(s"allFailures: $allFailures")
    println(s"allFailures == allFailures2: ${allFailures == allFailures2}")
    println(s"event1Nel: $event1Nel")
    println(s"failure1Nel: $failure2Nel")
    println(s"event1 == event1Nel: " + (event1 == event1Nel))
    println(s"allFailuresNel: $allFailuresNel")
    println(s"allEventsNel: $allEventsNel")
    println("")

    printResult(allJobs)
    printResult(jobsFailed)

    println("")
  }

  def printResult(result: ValidationNel[String, Any]): Unit = result match {
    case Success(value) => println(s"Success, $value")
    case Failure(errs) => println(s"Failure, $errs")
  }
  
}