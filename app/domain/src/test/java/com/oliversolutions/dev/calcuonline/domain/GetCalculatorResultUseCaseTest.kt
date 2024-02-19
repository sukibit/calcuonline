package com.oliversolutions.dev.calcuonline.domain

import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCalculatorResultUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCalculatorResultUseCaseTest {

    private lateinit var getCalculatorResultUseCase: GetCalculatorResultUseCase
    private lateinit var mockCalculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        mockCalculatorRepository = mock()
        getCalculatorResultUseCase = GetCalculatorResultUseCase(mockCalculatorRepository)
    }

    @Test
    fun `execute should delegate to calculatorRepository`() = runBlocking {
        val calculatorQuery = CalculatorQuery(input = "2+2", calculatorType = CalculatorType.AGE)
        val expectedResult = "4"
        whenever(mockCalculatorRepository.getCalculatorResult(calculatorQuery)).thenReturn(expectedResult)
        val result = getCalculatorResultUseCase(calculatorQuery)
        verify(mockCalculatorRepository).getCalculatorResult(calculatorQuery)
        assertEquals(Result.success(expectedResult), (result))
    }

    @Test(expected = Exception::class)
    fun `execute should return failure if an exception is thrown`() {
        runBlocking {
            val calculatorQuery = CalculatorQuery(input = "2+2", calculatorType = CalculatorType.AGE)
            val expectedException = RetrofitException("Some error")
            whenever(mockCalculatorRepository.getCalculatorResult(calculatorQuery)).thenThrow(expectedException)
            val result = getCalculatorResultUseCase(calculatorQuery)
            assertEquals(Result.failure<String>(expectedException), result)
            verify(mockCalculatorRepository).getCalculatorResult(calculatorQuery)
        }
    }
}
