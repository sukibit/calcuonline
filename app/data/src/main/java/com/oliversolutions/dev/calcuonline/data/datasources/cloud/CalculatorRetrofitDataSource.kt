package com.oliversolutions.dev.calcuonline.data.datasources.cloud

import com.oliversolutions.dev.calcuonline.data.models.CalculatorQueryData

interface CalculatorRetrofitDataSource {
    suspend fun getCalculatorResult(calculatorQueryData: CalculatorQueryData): String
}