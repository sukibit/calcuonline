package com.oliversolutions.dev.calcuonline.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oliversolutions.dev.calcuonline.R
import com.oliversolutions.dev.calcuonline.domain.exceptions.DomainException

abstract class BaseViewModel(private val application: Application) : AndroidViewModel(application) {

    private val _showSnackBar = MutableLiveData<String>()
    val showSnackBar: LiveData<String> = _showSnackBar

    fun resetSnackbar() {
        _showSnackBar.value = null
    }

    protected fun resolveError(exception: Throwable) {
        when (exception) {
            is DomainException.DatabaseException ->
                _showSnackBar.value = application.getString(R.string.database_error)

            is DomainException.RetrofitException ->
                _showSnackBar.value = application.getString(R.string.retrofit_error)

            is DomainException.FirebaseException ->
                _showSnackBar.value = application.getString(R.string.firebase_error)

            else ->
                _showSnackBar.value = exception.message
        }
    }
}