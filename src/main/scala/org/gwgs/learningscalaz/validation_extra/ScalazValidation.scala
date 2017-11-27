package org.gwgs.learningscalaz.validation_extra

trait ScalazValidation {
  import scalaz._
  import Scalaz._

  type ValidatedNel[T] = ValidationNel[String, T]

  def stringValueNel(values: Seq[String]): ValidatedNel[ValidValue] =
    if (values.isEmpty || values.head.isEmpty) "Empty value".failureNel[ValidValue]
    else MkValue(values.head).successNel[String]

  def emptyValuesNel(values: Seq[String]): ValidatedNel[ValidValue] =
    if (values.isEmpty || values.exists(_.isEmpty)) s"Empty value in $values".failureNel[ValidValue]
    else MkValue(values).successNel[String]

  def intValueNel(values: Seq[String]): ValidatedNel[ValidValue] = {
    if (values.isEmpty) "Empty value".failureNel[ValidValue]
    else values.head.parseInt.leftMap(_.toString).map(MkValue(_)).toValidationNel
  }
}

/**
  * polymorphic type
  * type F[A] = SomeClass[A]
  *
  * Existential type:
  * type F = SomeClass[A] forSome { type A }
  *
  * Phantom type:
  * type F[A] = SomeClass
  */
sealed trait ValidValue {
  type Inner
  val value: Inner
}

/**
  * ValidValue is an existential type
  *
  * MkValue is a type eraser, after compilation, the type information of A is lost
  */
final case class MkValue[A](value: A) extends ValidValue { type Inner = A }
