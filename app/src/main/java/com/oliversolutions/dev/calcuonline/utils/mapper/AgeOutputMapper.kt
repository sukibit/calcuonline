package com.oliversolutions.dev.calcuonline.utils.mapper

import com.google.gson.Gson
import com.oliversolutions.dev.calcuonline.presentation.models.AgeCalculatorOutput
import com.oliversolutions.dev.calcuonline.presentation.models.CalculatorOutput

object AgeOutputMapper : OutputMapper<CalculatorOutput> {
    override fun map(output: String): AgeCalculatorOutput? {
        return try {
            Gson().fromJson(output, AgeCalculatorOutput::class.java)
        } catch (e: Exception) {
            null
        }
    }
}