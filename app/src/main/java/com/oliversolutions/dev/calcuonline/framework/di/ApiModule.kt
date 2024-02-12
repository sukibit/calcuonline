package com.oliversolutions.dev.calcuonline.framework.di

import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.CalculatorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val API_BASE_URL = "https://api.calcuonline.com/"

    @Provides
    fun provideCalculatorApi(retrofit: Retrofit): CalculatorApi = retrofit.create(CalculatorApi::class.java)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

}
