package com.oliversolutions.dev.calcuonline.data.repositories

import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorDatabaseDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorFirebaseDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorRetrofitDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorCacheDataSource
import com.oliversolutions.dev.calcuonline.data.models.CalculatorQueryData
import com.oliversolutions.dev.calcuonline.data.models.CalculatorTypeData
import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.domain.models.Category
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import javax.inject.Inject

class CalculatorRepositoryImpl @Inject constructor(
    private val firebaseDataSource: CalculatorFirebaseDataSource,
    private val databaseDataSource: CalculatorDatabaseDataSource,
    private val cacheDataSource: CalculatorCacheDataSource,
    private val apiDataSource: CalculatorRetrofitDataSource,
) : CalculatorRepository, BaseRepository() {

    override suspend fun getCalculators(): List<Calculator> {
        return run {
            databaseDataSource.getCalculators().map {
                it.toDomain()
            }
        }
    }

    override suspend fun getCategories(): List<Category> {
        return run {
            databaseDataSource.getCategories().map {
                it.toDomain()
            }
        }
    }

    override suspend fun getCalculatorResult(calculatorQuery: CalculatorQuery): String {
        return run {
            apiDataSource.getCalculatorResult(calculatorQuery.toData())
        }
    }

    override suspend fun loadData() {
        run {
            if (!cacheDataSource.firebaseDataIsImported()) {
                databaseDataSource.saveCategories(firebaseDataSource.getCategories())
                databaseDataSource.saveCalculators(firebaseDataSource.getCalculators())
                cacheDataSource.flagFirebaseDataAsImported()
            }
        }
    }

    private fun CalculatorQuery.toData() = CalculatorQueryData(
        input = this.input,
        calculatorType = when (this.calculatorType) {
            CalculatorType.AGE -> CalculatorTypeData.AGE
            CalculatorType.AVERAGE_SPEED -> CalculatorTypeData.AVERAGE_SPEED
            CalculatorType.ASCENDANT -> CalculatorTypeData.ASCENDANT
            CalculatorType.BABY_GENDER -> CalculatorTypeData.BABY_GENDER
            CalculatorType.BIKE_SIZE -> CalculatorTypeData.BIKE_SIZE
            CalculatorType.BINARY -> CalculatorTypeData.BINARY
            CalculatorType.BIORHYTHM -> CalculatorTypeData.BIORHYTHM
            CalculatorType.BMI -> CalculatorTypeData.BMI
            CalculatorType.BMR -> CalculatorTypeData.BMR
            CalculatorType.BODY_FAT -> CalculatorTypeData.BODY_FAT
        }
    )
}