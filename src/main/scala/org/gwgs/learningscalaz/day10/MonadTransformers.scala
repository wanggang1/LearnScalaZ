package org.gwgs.learningscalaz.day10

object MonadTransformers {
  import scalaz._
  import Scalaz._

  //Reader's return value is String
  def myName(step: String): Reader[String, String] = Reader {step + ", I am " + _}

  //Reader is instance of Kleisli???
  def localExample: Reader[String, (String, String, String)] = for {
    a <- myName("First")
    b <- myName("Second") >=> Reader { _ + "dy"}
    c <- myName("Third") >=> Reader { _ + "ny"}
  } yield (a, b, c)


  // ReaderT, monad transformer version of Reader
  //   - ReaderT[Option,A,B] monad combines Reader’s ability to read from
  //     some configuration once, and Option’s ability to express failure.
  //   - ReaderT[Option,A,B]'s return value is Option[B]
  type ReaderTOption[A, B] = ReaderT[Option, A, B]

  object ReaderTOption {
    def apply[A, B](f: A => Option[B]): ReaderTOption[A, B] = Kleisli(f) //Kleisli[M[_], A, B] is a wrapper of A => M[B]
  }

  def configure(key: String): ReaderTOption[Map[String, String], String] =
    ReaderTOption[Map[String, String], String]( _.get(key) )

  def setupConnection: ReaderTOption[Map[String, String], (String, String, String)] =
    for {
      host <- configure("host")
      user <- configure("user")
      password <- configure("password")
    } yield (host, user, password)


  def demo: Unit = {
    val goodConfig = Map(
      "host" -> "eed3si9n.com",
      "user" -> "sa",
      "password" -> "****"
    )

    val badConfig = Map(
      "host" -> "example.com",
      "user" -> "sa"
    )

    //Return value is not Monad
    val mult: (String, String, String) = localExample("Gang")
    println(mult)

    //Read in the configuration once, setupConnection will use the configuation multiple times
    //Return value is Option
    val configRead: Id.Id[Option[(String, String, String)]] = setupConnection(goodConfig)
    val setupFailure: Id.Id[Option[(String, String, String)]] = setupConnection(badConfig)
    println(configRead)
    println(setupFailure)
  }
}
