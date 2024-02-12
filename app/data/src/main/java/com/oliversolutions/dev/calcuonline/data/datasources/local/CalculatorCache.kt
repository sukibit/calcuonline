package com.oliversolutions.dev.calcuonline.data.datasources.local

interface CalculatorCacheDataSource {
    fun flagFirebaseDataAsImported()
    fun firebaseDataIsImported(): Boolean
}