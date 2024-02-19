package com.oliversolutions.dev.calcuonline.data.repositories

import com.oliversolutions.dev.calcuonline.data.exceptions.DataException
import com.oliversolutions.dev.calcuonline.domain.exceptions.DomainException

abstract class BaseRepository {
    protected suspend fun <T> run(
        block: suspend () -> T
    ): T {
        return try {
            block()
        } catch (e: Throwable) {
            throw mapToDomainException(e)
        }
    }

    protected open fun mapToDomainException(e: Throwable): Exception {
        return when (e) {
            is DataException.FirebaseException -> DomainException.FirebaseException(e.message.orEmpty(), e)
            is DataException.DatabaseException -> DomainException.DatabaseException(e.message.orEmpty(), e)
            is DataException.RetrofitException -> DomainException.RetrofitException(e.message.orEmpty(), e)
            else -> DomainException.RepositoryException(e.message.orEmpty(), e)
        }
    }
}