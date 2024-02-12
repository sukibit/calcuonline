package com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)
}