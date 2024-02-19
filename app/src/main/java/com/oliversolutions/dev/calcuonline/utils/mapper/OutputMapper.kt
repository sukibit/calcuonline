package com.oliversolutions.dev.calcuonline.utils.mapper

interface OutputMapper<CalculatorOutput> {
    fun map(output: String): CalculatorOutput?
}