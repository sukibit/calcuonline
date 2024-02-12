package com.oliversolutions.dev.calcuonline.domain.repositories

import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.Category

interface CalculatorRepository {
    suspend fun getCalculators(): List<Calculator>
    suspend fun getCategories(): List<Category>
    suspend fun getCalculatorResult(calculatorQuery: CalculatorQuery): String
    suspend fun loadData()
}