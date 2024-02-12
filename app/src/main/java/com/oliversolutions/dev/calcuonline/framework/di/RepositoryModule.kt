package com.oliversolutions.dev.calcuonline.framework.di

import android.content.Context
import com.oliversolutions.dev.calcuonline.data.repositories.CalculatorRepositoryImpl
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import com.oliversolutions.dev.calcuonline.framework.di.CloudDataSourceModule.provideCalculatorFirebaseDatasource
import com.oliversolutions.dev.calcuonline.framework.di.CloudDataSourceModule.provideCalculatorRetrofitDatasource
import com.oliversolutions.dev.calcuonline.framework.di.LocalDataSourceModule.provideCalculatorCacheDatasource
import com.oliversolutions.dev.calcuonline.framework.di.LocalDataSourceModule.provideCalculatorDatabaseDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCalculatorRepository(@ApplicationContext context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl(
            provideCalculatorFirebaseDatasource(),
            provideCalculatorDatabaseDatasource(context),
            provideCalculatorCacheDatasource(),
            provideCalculatorRetrofitDatasource(),
        )
    }
}
