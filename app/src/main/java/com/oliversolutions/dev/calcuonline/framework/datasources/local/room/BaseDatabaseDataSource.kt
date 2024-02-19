package com.oliversolutions.dev.calcuonline.framework.datasources.local.room

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.exceptions.DataException
import javax.inject.Inject

open class BaseDatabaseDataSource @Inject constructor(private val crashlytics: FirebaseCrashlytics)  {
    protected suspend fun <T> executeDatabaseOperation(operation: suspend () -> T): T {
        return try {
            operation()
        } catch (e: Exception) {
            crashlytics.recordException(e)
            throw DataException.DatabaseException("Database operation failed", e)
        }
    }
}
