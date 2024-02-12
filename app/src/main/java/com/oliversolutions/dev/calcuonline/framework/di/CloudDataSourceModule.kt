package com.oliversolutions.dev.calcuonline.framework.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorFirebaseDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorRetrofitDataSource
import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.firebase.CalculatorFirebaseDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.CalculatorRetrofitDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.di.ApiModule.provideCalculatorApi
import com.oliversolutions.dev.calcuonline.framework.di.ApiModule.provideRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CloudDataSourceModule {

    @Provides
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
        return FirebaseCrashlytics.getInstance()
    }

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideCalculatorFirebaseDatasource(): CalculatorFirebaseDataSource {
        return CalculatorFirebaseDataSourceImpl(
            provideFirebaseFirestore(),
            provideFirebaseCrashlytics(),
        )
    }

    @Provides
    fun provideCalculatorRetrofitDatasource(): CalculatorRetrofitDataSource {
        return CalculatorRetrofitDataSourceImpl(
            provideCalculatorApi(provideRetrofit()),
            provideFirebaseCrashlytics(),
        )
    }
}
