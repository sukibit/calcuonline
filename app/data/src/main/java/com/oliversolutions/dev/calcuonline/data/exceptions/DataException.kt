package com.oliversolutions.dev.calcuonline.data.exceptions

sealed class DataException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class DatabaseException(message: String, cause: Throwable? = null) : DataException(message, cause)
    class FirebaseException(message: String, cause: Throwable? = null) : DataException(message, cause)
    class RetrofitException(message: String, cause: Throwable? = null) : DataException(message, cause)
}