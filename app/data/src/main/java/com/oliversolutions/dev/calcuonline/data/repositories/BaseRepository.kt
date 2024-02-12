package com.oliversolutions.dev.calcuonline.data.repositories

import com.oliversolutions.dev.calcuonline.data.exceptions.DatabaseDataException
import com.oliversolutions.dev.calcuonline.data.exceptions.FirebaseDataException
import com.oliversolutions.dev.calcuonline.data.exceptions.RetrofitDataException
import com.oliversolutions.dev.calcuonline.domain.exceptions.DatabaseException
import com.oliversolutions.dev.calcuonline.domain.exceptions.FirebaseException
import com.oliversolutions.dev.calcuonline.domain.exceptions.RepositoryException
import com.oliversolutions.dev.calcuonline.domain.exceptions.RetrofitException

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
            is FirebaseDataException -> FirebaseException(e.message.orEmpty(), e)
            is DatabaseDataException -> DatabaseException(e.message.orEmpty(), e)
            is RetrofitDataException -> RetrofitException(e.message.orEmpty(), e)
            else -> RepositoryException(e.message.orEmpty(), e)
        }
    }
}