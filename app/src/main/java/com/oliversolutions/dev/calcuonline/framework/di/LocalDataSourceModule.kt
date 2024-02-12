package com.oliversolutions.dev.calcuonline.framework.di

import android.content.Context
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorCacheDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorDatabaseDataSource
import com.oliversolutions.dev.calcuonline.framework.datasources.local.cache.CalculatorCacheDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.datasources.local.cache.HashMapCache
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.CalculatorDatabase
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.CalculatorDatabaseDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.di.CloudDataSourceModule.provideFirebaseCrashlytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    fun provideCalculatorDatabaseDatasource(@ApplicationContext context: Context): CalculatorDatabaseDataSource {
        return CalculatorDatabaseDataSourceImpl(
            provideCalculatorDatabase(context),
            provideFirebaseCrashlytics()
        )
    }

    @Provides
    fun provideCalculatorCacheDatasource(): CalculatorCacheDataSource {
        return CalculatorCacheDataSourceImpl(HashMapCache())
    }

    @Provides
    fun provideCalculatorDatabase(@ApplicationContext context: Context): CalculatorDatabase {
        return CalculatorDatabase.getInstance(context)
    }
}
