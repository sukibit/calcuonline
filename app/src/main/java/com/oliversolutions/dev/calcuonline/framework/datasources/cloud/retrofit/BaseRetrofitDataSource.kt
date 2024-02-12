package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.retrofit

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.oliversolutions.dev.calcuonline.data.exceptions.RetrofitDataException
import javax.inject.Inject

open class BaseRetrofitDataSource @Inject constructor(private val crashlytics: FirebaseCrashlytics) {
    protected suspend fun <T> executeRetrofitOperation(operation: suspend () -> T): T {
        return try {
            operation()
        } catch (e: Exception) {
            crashlytics.recordException(e)
            throw RetrofitDataException("Retrofit operation failed", e)
        }
    }
}