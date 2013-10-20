package models

case class NumberValidation(
	minValue: Int,
	maxValue: Int,
	rangeValue: Int,
	numberValue: Int,
	digitsValue: Int,
	phoneUS: String
)
