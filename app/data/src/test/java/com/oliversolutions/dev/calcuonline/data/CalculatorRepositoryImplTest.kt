package com.oliversolutions.dev.calcuonline.data

import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorFirebaseDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorRetrofitDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorCacheDataSource
import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorDatabaseDataSource
import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.models.CalculatorQueryData
import com.oliversolutions.dev.calcuonline.data.models.CalculatorTypeData
import com.oliversolutions.dev.calcuonline.data.models.CategoryData
import com.oliversolutions.dev.calcuonline.data.repositories.CalculatorRepositoryImpl
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CalculatorRepositoryImplTest {

    private lateinit var calculatorRepositoryImpl: CalculatorRepositoryImpl
    private lateinit var mockFirebaseDataSource: CalculatorFirebaseDataSource
    private lateinit var mockDatabaseDataSource: CalculatorDatabaseDataSource
    private lateinit var mockCacheDataSource: CalculatorCacheDataSource
    private lateinit var mockApiDataSource: CalculatorRetrofitDataSource

    @Before
    fun setup() {
        mockFirebaseDataSource = mock()
        mockDatabaseDataSource = mock()
        mockCacheDataSource = mock()
        mockApiDataSource = mock()

        calculatorRepositoryImpl = CalculatorRepositoryImpl(
            mockFirebaseDataSource,
            mockDatabaseDataSource,
            mockCacheDataSource,
            mockApiDataSource
        )
    }

    @Test
    fun `test getCalculators`() {
        runBlocking {
            val calculatorDataList = listOf(
                CalculatorData(
                    resId = "1",
                    isFavorite = true,
                    isFeatured = false,
                    iconUrl = "https://example.com/icon1.png",
                    categoryId = 1L
                ),
                CalculatorData(
                    resId = "2",
                    isFavorite = false,
                    isFeatured = true,
                    iconUrl = "https://example.com/icon2.png",
                    categoryId = 2L
                ),
            )
            whenever(mockDatabaseDataSource.getCalculators()).thenReturn(calculatorDataList)
            val result = calculatorRepositoryImpl.getCalculators()
            assertEquals(result[0].resId, "1")
            assertEquals(result[0].isFavorite, true)
            assertEquals(result[0].isFeatured, false)
            assertEquals(result[0].iconUrl, "https://example.com/icon1.png")
            assertEquals(result[0].categoryId, 1L)
        }
    }

    @Test
    fun `test getCategories`() {
        val categoriesList = listOf(
            CategoryData(
                id = 1L,
                iconUrl = "https://example.com/icon1.png",
                resId = "category1"
            ),
            CategoryData(
                id = 2L,
                iconUrl = "https://example.com/icon2.png",
                resId = "category2"
            ),
            CategoryData(
                id = 3L,
                iconUrl = "https://example.com/icon3.png",
                resId = "category3"
            ),
        )
        runBlocking {
            whenever(mockDatabaseDataSource.getCategories()).thenReturn(categoriesList)
            val result = calculatorRepositoryImpl.getCategories()
            assertEquals(result[0].resId, "category1")
            assertEquals(result[0].id, 1L)
            assertEquals(result[0].iconUrl, "https://example.com/icon1.png")
        }
    }

    @Test
    fun `test getCalculatorResult`() {
        val calculatorQuery = CalculatorQuery(CalculatorType.AGE, "input")
        val calculatorQueryData = CalculatorQueryData(CalculatorTypeData.AGE, "input")
        val expectedResponse = "Result"
        runBlocking {
            whenever(mockApiDataSource.getCalculatorResult(calculatorQueryData)).thenReturn(expectedResponse)
            val result = calculatorRepositoryImpl.getCalculatorResult(calculatorQuery)
            assertEquals(expectedResponse, result)
        }
    }

    @Test
    fun `test loadData`() {
        runBlocking {
            whenever(mockCacheDataSource.firebaseDataIsImported()).thenReturn(false)
            val categoriesList = listOf(
                CategoryData(
                    id = 1L,
                    iconUrl = "https://example.com/icon1.png",
                    resId = "category1"
                ),
                CategoryData(
                    id = 2L,
                    iconUrl = "https://example.com/icon2.png",
                    resId = "category2"
                ),
                CategoryData(
                    id = 3L,
                    iconUrl = "https://example.com/icon3.png",
                    resId = "category3"
                ),
            )
            val calculatorDataList = listOf(
                CalculatorData(
                    resId = "1",
                    isFavorite = true,
                    isFeatured = false,
                    iconUrl = "https://example.com/icon1.png",
                    categoryId = 1L
                ),
                CalculatorData(
                    resId = "2",
                    isFavorite = false,
                    isFeatured = true,
                    iconUrl = "https://example.com/icon2.png",
                    categoryId = 2L
                ),
            )
            whenever(mockFirebaseDataSource.getCategories()).thenReturn(categoriesList)
            whenever(mockFirebaseDataSource.getCalculators()).thenReturn(calculatorDataList)
            whenever(mockCacheDataSource.firebaseDataIsImported()).thenReturn(false)

            calculatorRepositoryImpl.loadData()
            verify(mockDatabaseDataSource).saveCategories(categoriesList)
            verify(mockDatabaseDataSource).saveCalculators(calculatorDataList)
            verify(mockCacheDataSource).flagFirebaseDataAsImported()
        }
    }

    @Test
    fun `test loadData when flag is true should not load anything`() {
        runBlocking {
            whenever(mockCacheDataSource.firebaseDataIsImported()).thenReturn(false)
            val categoriesList = listOf(
                CategoryData(
                    id = 1L,
                    iconUrl = "https://example.com/icon1.png",
                    resId = "category1"
                ),
                CategoryData(
                    id = 2L,
                    iconUrl = "https://example.com/icon2.png",
                    resId = "category2"
                ),
                CategoryData(
                    id = 3L,
                    iconUrl = "https://example.com/icon3.png",
                    resId = "category3"
                ),
            )
            val calculatorDataList = listOf(
                CalculatorData(
                    resId = "1",
                    isFavorite = true,
                    isFeatured = false,
                    iconUrl = "https://example.com/icon1.png",
                    categoryId = 1L
                ),
                CalculatorData(
                    resId = "2",
                    isFavorite = false,
                    isFeatured = true,
                    iconUrl = "https://example.com/icon2.png",
                    categoryId = 2L
                ),
            )
            whenever(mockFirebaseDataSource.getCategories()).thenReturn(categoriesList)
            whenever(mockFirebaseDataSource.getCalculators()).thenReturn(calculatorDataList)
            whenever(mockCacheDataSource.firebaseDataIsImported()).thenReturn(true)

            calculatorRepositoryImpl.loadData()
            verify(mockDatabaseDataSource, never()).saveCategories(categoriesList)
            verify(mockDatabaseDataSource, never()).saveCalculators(calculatorDataList)
            verify(mockCacheDataSource, never()).flagFirebaseDataAsImported()
        }
    }
}
