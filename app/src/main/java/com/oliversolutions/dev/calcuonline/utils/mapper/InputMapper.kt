package com.oliversolutions.dev.calcuonline.utils.mapper

interface InputMapper<CalculatorInput> {
    fun map(input: String): CalculatorInput?
}