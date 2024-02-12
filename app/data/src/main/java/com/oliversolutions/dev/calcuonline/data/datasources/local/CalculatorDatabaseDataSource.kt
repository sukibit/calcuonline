package com.oliversolutions.dev.calcuonline.data.datasources.local

import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.models.CategoryData

interface CalculatorDatabaseDataSource {
    suspend fun getCalculators(): List<CalculatorData>
    suspend fun getCategories(): List<CategoryData>
    suspend fun saveCategories(categories: List<CategoryData>)
    suspend fun saveCalculators(calculators: List<CalculatorData>)
}