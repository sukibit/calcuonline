package com.oliversolutions.dev.calcuonline.datasources

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.models.CalculatorQueryData
import com.oliversolutions.dev.calcuonline.data.models.CalculatorTypeData
import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.CalculatorApi
import com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit.CalculatorRetrofitDataSourceImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CalculatorRetrofitDataSourceImplTest {

    private lateinit var mockCalculatorApi: CalculatorApi
    private lateinit var calculatorRetrofitDataSourceImpl: CalculatorRetrofitDataSourceImpl
    private lateinit var mockFirebaseCrashlytics: FirebaseCrashlytics

    @Before
    fun setup() {
        mockFirebaseCrashlytics = mock()
        mockCalculatorApi = mock()
        calculatorRetrofitDataSourceImpl = CalculatorRetrofitDataSourceImpl(mockCalculatorApi, mockFirebaseCrashlytics)
    }

    @Test
    fun `test getCalculatorResult`() = runBlocking {
        val expectedResponse = "Result"
        whenever(mockCalculatorApi.getCalculatorResult("calculators/calculator-age", "input")).thenReturn(expectedResponse)
        val result = calculatorRetrofitDataSourceImpl.getCalculatorResult(
            CalculatorQueryData(
                CalculatorTypeData.AGE,
                "input"
            )
        )
        assertEquals(expectedResponse, result)
    }

    @Test(expected = Exception::class)
    fun `test getCalculatorResult with error`() = runBlocking {
        val errorMessage = "Test error message"
        whenever(mockCalculatorApi.getCalculatorResult("calculators/calculator-age", "input")).thenThrow(Exception(errorMessage))
        calculatorRetrofitDataSourceImpl.getCalculatorResult(
            CalculatorQueryData(
                CalculatorTypeData.AGE,
                "input"
            )
        )
        verify(mockFirebaseCrashlytics).recordException(any())
    }
}
