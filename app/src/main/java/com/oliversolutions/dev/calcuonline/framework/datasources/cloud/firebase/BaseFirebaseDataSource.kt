package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.exceptions.DataException
import javax.inject.Inject

open class BaseFirebaseDataSource @Inject constructor(private val crashlytics: FirebaseCrashlytics) {
    protected suspend fun <T> executeFirebaseOperation(operation: suspend () -> T): T {
        return try {
            operation()
        } catch (e: Exception) {
            crashlytics.recordException(e)
            throw DataException.FirebaseException("Firebase operation failed", e)
        }
    }
}