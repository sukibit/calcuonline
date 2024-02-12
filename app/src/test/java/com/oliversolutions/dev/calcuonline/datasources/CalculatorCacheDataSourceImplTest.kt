package com.oliversolutions.dev.calcuonline.datasources

import com.oliversolutions.dev.calcuonline.framework.datasources.local.cache.CalculatorCacheDataSourceImpl
import com.oliversolutions.dev.calcuonline.framework.datasources.local.cache.HashMapCache
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CalculatorCacheDataSourceImplTest {

    private val mockHashMapCache: HashMapCache = mock()
    private val calculatorCacheDataSourceImpl = CalculatorCacheDataSourceImpl(mockHashMapCache)

    @Test
    fun `test flagFirebaseDataAsImported`() {
        calculatorCacheDataSourceImpl.flagFirebaseDataAsImported()
        verify(mockHashMapCache).saveData(CalculatorCacheDataSourceImpl.FIREBASE_IS_IMPORTED, true)
    }

    @Test
    fun `test firebaseDataIsImported when data is saved as true`() {
        whenever(mockHashMapCache.getData(CalculatorCacheDataSourceImpl.FIREBASE_IS_IMPORTED)).thenReturn(true)
        val result = calculatorCacheDataSourceImpl.firebaseDataIsImported()
        assertTrue(result)
    }

    @Test
    fun `test firebaseDataIsImported when data is saved as false`() {
        whenever(mockHashMapCache.getData(CalculatorCacheDataSourceImpl.FIREBASE_IS_IMPORTED)).thenReturn(false)
        val result = calculatorCacheDataSourceImpl.firebaseDataIsImported()
        assertFalse(result)
    }

    @Test
    fun `test firebaseDataIsImported when data is not present`() {
        whenever(mockHashMapCache.getData(CalculatorCacheDataSourceImpl.FIREBASE_IS_IMPORTED)).thenReturn(null)
        val result = calculatorCacheDataSourceImpl.firebaseDataIsImported()
        assertFalse(result)
    }
}
