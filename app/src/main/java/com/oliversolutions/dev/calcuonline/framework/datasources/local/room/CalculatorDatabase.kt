package com.oliversolutions.dev.calcuonline.framework.datasources.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao.CalculatorDao
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao.CategoryDao
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CalculatorEntity
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CategoryEntity

@Database(entities = [CategoryEntity::class, CalculatorEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun calculatorDao(): CalculatorDao

    companion object {
        @Volatile
        private var INSTANCE: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalculatorDatabase::class.java,
                    "calcuonline"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
