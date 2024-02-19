package com.oliversolutions.dev.calcuonline.utils.mapper

import com.oliversolutions.dev.calcuonline.presentation.models.AgeCalculatorInput
import com.oliversolutions.dev.calcuonline.presentation.models.CalculatorInput

object AgeInputMapper : InputMapper<CalculatorInput> {
    override fun map(input: String): AgeCalculatorInput? {
        return try {
            val components = input.split("/")
            val birthDateMutableList = mutableMapOf(
                "day" to components[0].toInt(),
                "month" to components[1].toInt(),
                "year" to components[2].toInt(),
            )
            AgeCalculatorInput(birthDateMutableList)
        } catch (e: Exception) {
            null
        }
    }
}