package com.oliversolutions.dev.calcuonline.domain

import com.oliversolutions.dev.calcuonline.domain.models.Category
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCategoriesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCategoriesUseCaseTest {

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var mockCalculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        mockCalculatorRepository = mock()
        getCategoriesUseCase = GetCategoriesUseCase(mockCalculatorRepository)
    }

    @Test
    fun `execute should delegate to calculatorRepository`() = runBlocking {
        val categoriesList = listOf(
            Category(
                id = 1L,
                iconUrl = "https://example.com/icon1.png",
                resId = "category1"
            ),
            Category(
                id = 2L,
                iconUrl = "https://example.com/icon2.png",
                resId = "category2"
            ),
            Category(
                id = 3L,
                iconUrl = "https://example.com/icon3.png",
                resId = "category3"
            ),
        )
        whenever(mockCalculatorRepository.getCategories()).thenReturn(categoriesList)
        val result = getCategoriesUseCase()
        verify(mockCalculatorRepository).getCategories()
        Assert.assertEquals(Result.success(categoriesList), result)
    }

    @Test(expected = Exception::class)
    fun `execute should return failure if an exception is thrown`() {
        runBlocking {
            val expectedException = DatabaseException("Some error")
            whenever(mockCalculatorRepository.getCategories()).thenThrow(expectedException)
            val result = getCategoriesUseCase()
            Assert.assertEquals(Result.failure<List<Category>>(expectedException), result)
            verify(mockCalculatorRepository).getCategories()
        }
    }
}
