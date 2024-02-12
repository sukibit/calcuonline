package com.oliversolutions.dev.calcuonline.domain.models

data class CalculatorQuery(
    val calculatorType: CalculatorType,
    val input: String,
)