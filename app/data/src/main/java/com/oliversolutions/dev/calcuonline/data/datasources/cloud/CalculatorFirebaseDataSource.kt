package com.oliversolutions.dev.calcuonline.data.datasources.cloud

import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.models.CategoryData

interface CalculatorFirebaseDataSource {
    suspend fun getCategories(): List<CategoryData>
    suspend fun getCalculators(): List<CalculatorData>
}