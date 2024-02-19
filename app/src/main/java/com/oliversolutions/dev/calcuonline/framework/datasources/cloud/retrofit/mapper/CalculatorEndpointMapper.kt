package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.mapper

import com.oliversolutions.dev.calcuonline.data.models.CalculatorTypeData

object CalculatorEndpointMapper {
    fun mapCalculatorTypeToEndpoint(calculatorType: CalculatorTypeData): String {
        return when (calculatorType) {
            CalculatorTypeData.AGE -> "calculators/calculator-age"
        }
    }
}
