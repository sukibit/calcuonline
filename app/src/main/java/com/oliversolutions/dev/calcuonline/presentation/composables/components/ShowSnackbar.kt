package com.oliversolutions.dev.calcuonline.presentation.composables.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.oliversolutions.dev.calcuonline.presentation.composables.components.SnackbarHostStateSingleton.provideSnackbarHostState
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SnackbarHostStateSingleton {
    private val snackbarHostState: SnackbarHostState by lazy { SnackbarHostState() }
    fun provideSnackbarHostState(): SnackbarHostState {
        return snackbarHostState
    }
}

@Composable
fun showSnackbar(message: String, scope: CoroutineScope, viewModel: BaseViewModel) {
    val snackbarHostState = remember { provideSnackbarHostState() }
    scope.launch {
        snackbarHostState.showSnackbar(
            message
        )
        viewModel.resetSnackbar()
    }
}