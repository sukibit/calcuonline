package com.oliversolutions.dev.calcuonline.framework.datasources.local.room

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorDatabaseDataSource
import com.oliversolutions.dev.calcuonline.data.models.CategoryData
import com.oliversolutions.dev.calcuonline.utils.toData
import com.oliversolutions.dev.calcuonline.utils.toEntity
import javax.inject.Inject


class CalculatorDatabaseDataSourceImpl @Inject constructor(
    private val calculatorDatabase: CalculatorDatabase,
    crashlytics: FirebaseCrashlytics,
) : CalculatorDatabaseDataSource, BaseDatabaseDataSource(crashlytics) {

    override suspend fun getCalculators(): List<CalculatorData> {
        return executeDatabaseOperation {
            calculatorDatabase.calculatorDao().getCalculators().map {
                it.toData()
            }
        }
    }

    override suspend fun getCategories(): List<CategoryData> {
        return executeDatabaseOperation {
            calculatorDatabase.categoryDao().getAllCategories().map {
                it.toData()
            }
        }
    }

    override suspend fun saveCategories(categories: List<CategoryData>) {
        return executeDatabaseOperation {
            calculatorDatabase.categoryDao().insertCategories(
                categories.map {
                    it.toEntity()
                }
            )
        }
    }

    override suspend fun saveCalculator(calculatorData: CalculatorData) {
        return executeDatabaseOperation {
            calculatorDatabase.calculatorDao().insertCalculator(
                calculatorData.toEntity()
            )
        }
    }

    override suspend fun saveCalculators(calculators: List<CalculatorData>) {
        return executeDatabaseOperation {
            calculatorDatabase.calculatorDao().insertCalculators(
                calculators.map {
                    it.toEntity()
                }
            )
        }
    }
}