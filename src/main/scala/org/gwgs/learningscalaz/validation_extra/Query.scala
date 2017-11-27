package org.gwgs.learningscalaz.validation_extra

sealed trait Query
case class FilterQuery[T](field: String, values: T, operator: String) extends Query

case class CategoryConfig(category: String,
                          displayName: String,
                          dataField: String,
                          dataType: String,
                          dataOperators: Array[String],
                          dataValues: Option[Array[String]],
                          operation: String)

object CategoryConfig {
  val SearchOperation = "search"
  val FilterOperation = "filter"
  val NumericDataType = "numeric"
  val StringDataType = "string"
}

