package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.mapper

import com.oliversolutions.dev.calcuonline.data.models.CalculatorTypeData

object CalculatorEndpointMapper {
    fun mapCalculatorTypeToEndpoint(calculatorType: CalculatorTypeData): String {
        return when (calculatorType) {
            CalculatorTypeData.AGE -> "calculators/calculator-age"
            CalculatorTypeData.AVERAGE_SPEED -> "calculators/calculator-averagespeed"
            CalculatorTypeData.ASCENDANT -> "calculators/calculator-ascendant"
            CalculatorTypeData.BABY_GENDER -> "calculators/calculator-sexofthebaby"
            CalculatorTypeData.BIKE_SIZE -> "calculators/calculator-bikesize"
            CalculatorTypeData.BINARY -> "calculators/calculator-binary"
            CalculatorTypeData.BIORHYTHM -> "calculators/calculator-biorhythm"
            CalculatorTypeData.BMI -> "calculators/calculator-bodymassindex"
            CalculatorTypeData.BMR -> "calculators/calculator-basalmetabolicrate"
            CalculatorTypeData.BODY_FAT -> "calculators/calculator-bodyfat"
        }
    }
}
