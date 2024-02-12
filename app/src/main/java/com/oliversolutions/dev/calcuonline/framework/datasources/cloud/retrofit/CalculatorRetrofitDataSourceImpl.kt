package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorRetrofitDataSource
import com.oliversolutions.dev.calcuonline.data.models.CalculatorQueryData
import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.mapper.CalculatorEndpointMapper
import javax.inject.Inject

class CalculatorRetrofitDataSourceImpl @Inject constructor(
    private val calculatorApi: CalculatorApi,
    crashlytics: FirebaseCrashlytics,
) : CalculatorRetrofitDataSource, BaseRetrofitDataSource(crashlytics) {
    override suspend fun getCalculatorResult(calculatorQueryData: CalculatorQueryData): String = executeRetrofitOperation {
        calculatorApi.getCalculatorResult(
            endPoint = CalculatorEndpointMapper.mapCalculatorTypeToEndpoint(calculatorQueryData.calculatorType),
            body = calculatorQueryData.input,
        )
    }
}