package com.oliversolutions.dev.calcuonline.framework.datasources.local.cache

import com.oliversolutions.dev.calcuonline.data.datasources.local.CalculatorCacheDataSource
import javax.inject.Inject

class CalculatorCacheDataSourceImpl @Inject constructor(
    private val hashMapCache: HashMapCache
) : CalculatorCacheDataSource {
    companion object {
        const val FIREBASE_IS_IMPORTED = "firebase_is_imported"
    }

    override fun flagFirebaseDataAsImported() {
        hashMapCache.saveData(FIREBASE_IS_IMPORTED, true)
    }

    override fun firebaseDataIsImported(): Boolean {
        return hashMapCache.getData(FIREBASE_IS_IMPORTED) as? Boolean ?: false
    }
}