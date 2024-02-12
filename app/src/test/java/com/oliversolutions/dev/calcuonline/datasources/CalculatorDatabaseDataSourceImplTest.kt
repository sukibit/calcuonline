package com.oliversolutions.dev.calcuonline.datasources

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.models.CategoryData
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.CalculatorDatabase
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.CalculatorDatabaseDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao.CalculatorDao
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.dao.CategoryDao
import com.oliversolutions.dev.calcuonline.utils.toEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CalculatorDatabaseDataSourceImplTest {

    private val mockCalculatorDatabase: CalculatorDatabase = mock()
    private val mockCalculatorDao: CalculatorDao = mock()
    private val mockCategoryDao: CategoryDao = mock()
    private val mockFirebaseCrashlytics: FirebaseCrashlytics = mock()
    private val calculatorDatabaseDataSourceImpl = CalculatorDatabaseDataSourceImpl(mockCalculatorDatabase, mockFirebaseCrashlytics)

    init {
        whenever(mockCalculatorDatabase.calculatorDao()).thenReturn(mockCalculatorDao)
        whenever(mockCalculatorDatabase.categoryDao()).thenReturn(mockCategoryDao)
    }

    @Test
    fun `test getCalculators`() = runBlocking {
        val expectedCalculators = listOf(
            CalculatorData("1", isFavorite = true, isFeatured = false, iconUrl = "url1", categoryId = 1),
            CalculatorData("2", isFavorite = false, isFeatured = true, iconUrl = "url2", categoryId = 2)
        )
        whenever(mockCalculatorDatabase.calculatorDao().getCalculators()).thenReturn(expectedCalculators.map { it.toEntity() })
        val result = calculatorDatabaseDataSourceImpl.getCalculators()
        assertEquals(expectedCalculators, result)
    }

    @Test
    fun `test getCategories`() = runBlocking {
        val expectedCategories = listOf(
            CategoryData(1, "Category 1", "res ID"),
            CategoryData(2, "Category 2", "res ID")
        )
        whenever(mockCalculatorDatabase.categoryDao().getAllCategories()).thenReturn(expectedCategories.map { it.toEntity() })
        val result = calculatorDatabaseDataSourceImpl.getCategories()
        assertEquals(expectedCategories, result)
    }

    @Test
    fun `test saveCategories`() = runBlocking {
        val categoriesToSave = listOf(
            CategoryData(1, "Category 1", "res ID"),
            CategoryData(2, "Category 2", "res ID")
        )
        calculatorDatabaseDataSourceImpl.saveCategories(categoriesToSave)
        verify(mockCalculatorDatabase.categoryDao()).insertCategories(categoriesToSave.map { it.toEntity() })
    }

    @Test
    fun `test saveCalculators`() = runBlocking {
        val calculatorsToSave = listOf(
            CalculatorData("1", isFavorite = true, isFeatured = false, iconUrl = "url1", categoryId = 1),
            CalculatorData("2", isFavorite = false, isFeatured = true, iconUrl = "url2", categoryId = 2)
        )
        calculatorDatabaseDataSourceImpl.saveCalculators(calculatorsToSave)
        verify(mockCalculatorDatabase.calculatorDao()).insertCalculators(calculatorsToSave.map { it.toEntity() })
    }

    @Test(expected = Exception::class)
    fun `test getCalculators with error`() = runBlocking {
        val errorMessage = "Test error message"
        whenever(mockCalculatorDatabase.calculatorDao().getCalculators()).thenThrow(Exception(errorMessage))
        calculatorDatabaseDataSourceImpl.getCalculators()
        verify(mockFirebaseCrashlytics).recordException(any())
    }
}
