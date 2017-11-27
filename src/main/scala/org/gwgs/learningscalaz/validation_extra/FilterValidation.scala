package org.gwgs.learningscalaz.validation_extra

object FilterValidation {
  def apply(configs: Seq[CategoryConfig]): FilterValidation = new FilterValidationScalazImpl(configs)

  def isFilterParam(key: String): Boolean =
    if (key == "per_page") false
    else {
      val filterOperations = key.split("_")
      filterOperations.length == 2
    }


  def isNumericFilter(category: String, configs: Seq[CategoryConfig]): Boolean = {
    configs.find(_.category == category) match {
      case Some(c) =>
        c.dataType == CategoryConfig.NumericDataType &&
          c.operation == CategoryConfig.FilterOperation
      case None =>
        false
    }
  }

  def isSearchFilter(category: String, configs: Seq[CategoryConfig]): Boolean = {
    configs.find(_.category == category) match {
      case Some(c) => c.operation == CategoryConfig.SearchOperation
      case None => false
    }
  }
}

trait FilterValidation {
  def filtersValidated(params: Map[String, Seq[String]]): Either[List[String], Seq[Query]]
}

class FilterValidationScalazImpl(configs: Seq[CategoryConfig]) extends FilterValidation with ScalazValidation {
  import FilterValidation._
  import scalaz._
  import Scalaz._

  def filtersValidated(params: Map[String, Seq[String]]): Either[List[String], Seq[Query]] = {
    val filters: List[(String, Seq[String])] = params.toList.filter(t => isFilterParam(t._1))

    filters
      .traverseU {
        case (key, values) =>
          val filterOperations = key.split("_")
          validate(filterOperations(0), filterOperations(1), values)
      }
      .leftMap(_.toList)
      .toEither
  }

  def validate(name: String, operator: String, values: Seq[String]): ValidatedNel[Query] = {
    val fieldName = nameNel(name)
    val value = valuesNel(name, values).map(_.value)
    val filterOperator = operatorNel(name, operator)

    (fieldName |@| value |@| filterOperator) {FilterQuery.apply _}
  }

  def nameNel(name: String): ValidatedNel[String] = {
    val fieldName: Option[String] = configs.collectFirst{
      case cc if cc.category == name => cc.dataField
    }

    fieldName match {
      case Some(dataField) => dataField.successNel[String]
      case None => s"Bad filter name: $name".failureNel[String]
    }
  }

  def operatorNel(name: String, operator: String): ValidatedNel[String] = {
    val operators: Option[Array[String]] = configs.collectFirst{
      case cc if cc.category == name => cc.dataOperators
    }

    operators match {
      case Some(operators) =>
        if (operators.contains(operator)) operator.successNel[String]
        else s"Bad operator, $operator, for $name".failureNel[String]
      case None =>
        operator.successNel[String]
    }
  }

  def valuesNel(name: String, values: Seq[String]): ValidatedNel[ValidValue] =
    if ( isSearchFilter(name, configs) ) {
      stringValueNel(values)
    } else if ( isNumericFilter(name, configs) ) {
      intValueNel(values)
    } else {
      emptyValuesNel(values)
    }

}
