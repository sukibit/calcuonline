package com.oliversolutions.dev.calcuonline.domain.exceptions

sealed class DomainException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class DatabaseException(message: String, cause: Throwable? = null) : DomainException(message, cause)
    class FirebaseException(message: String, cause: Throwable? = null) : DomainException(message, cause)
    class InvalidInputException(message: String, cause: Throwable? = null) : DomainException(message, cause)
    class RepositoryException(message: String, cause: Throwable? = null) : DomainException(message, cause)
    class RetrofitException(message: String, cause: Throwable? = null) : DomainException(message, cause)
}