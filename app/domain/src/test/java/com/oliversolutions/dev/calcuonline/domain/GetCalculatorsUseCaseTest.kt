package com.oliversolutions.dev.calcuonline.domain

import com.oliversolutions.dev.calcuonline.domain.exceptions.DatabaseException
import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCalculatorsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCalculatorsUseCaseTest {

    private lateinit var getCalculatorsUseCase: GetCalculatorsUseCase
    private lateinit var mockCalculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        mockCalculatorRepository = mock()
        getCalculatorsUseCase = GetCalculatorsUseCase(mockCalculatorRepository)
    }

    @Test
    fun `execute should delegate to calculatorRepository`() = runBlocking {
        val calculatorList = listOf(
            Calculator(
                resId = "calculator1",
                isFavorite = true,
                isFeatured = false,
                iconUrl = "https://example.com/calculator1.png",
                categoryId = 1L
            ),
            Calculator(
                resId = "calculator2",
                isFavorite = false,
                isFeatured = true,
                iconUrl = "https://example.com/calculator2.png",
                categoryId = 2L
            ),
        )
        whenever(mockCalculatorRepository.getCalculators()).thenReturn(calculatorList)
        val result = getCalculatorsUseCase()
        verify(mockCalculatorRepository).getCalculators()
        Assert.assertEquals(Result.success(calculatorList), result)
    }

    @Test(expected = Exception::class)
    fun `execute should return failure if an exception is thrown`() {
        runBlocking {
            val expectedException = DatabaseException("Some error")
            whenever(mockCalculatorRepository.getCalculators()).thenThrow(expectedException)
            val result = getCalculatorsUseCase()
            Assert.assertEquals(Result.failure<List<Calculator>>(expectedException), result)
            verify(mockCalculatorRepository).getCalculators()
        }
    }
}
