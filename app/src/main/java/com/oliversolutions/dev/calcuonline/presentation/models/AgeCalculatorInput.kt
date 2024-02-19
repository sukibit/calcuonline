package com.oliversolutions.dev.calcuonline.presentation.models

data class AgeCalculatorInput(
    val birthDate: MutableMap<String, Int>,
) : CalculatorInput