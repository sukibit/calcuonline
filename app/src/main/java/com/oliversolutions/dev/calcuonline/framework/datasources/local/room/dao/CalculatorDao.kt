package com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CalculatorEntity

@Dao
interface CalculatorDao {
    @Query("SELECT * FROM calculators WHERE categoryId = :categoryId")
    suspend fun getCalculatorsByCategoryId(categoryId: Int): List<CalculatorEntity>

    @Query("SELECT * FROM calculators")
    suspend fun getCalculators(): List<CalculatorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalculator(calculator: CalculatorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalculators(calculators: List<CalculatorEntity>)
}