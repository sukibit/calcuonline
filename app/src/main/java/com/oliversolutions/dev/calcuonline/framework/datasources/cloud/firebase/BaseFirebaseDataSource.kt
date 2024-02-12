package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.exceptions.FirebaseDataException
import javax.inject.Inject

open class BaseFirebaseDataSource @Inject constructor(private val crashlytics: FirebaseCrashlytics) {
    protected suspend fun <T> executeFirebaseOperation(operation: suspend () -> T): T {
        return try {
            operation()
        } catch (e: Exception) {
            crashlytics.recordException(e)
            throw FirebaseDataException("Firebase operation failed", e)
        }
    }
}