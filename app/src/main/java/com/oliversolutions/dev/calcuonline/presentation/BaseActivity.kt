package com.oliversolutions.dev.calcuonline.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.oliversolutions.dev.calcuonline.presentation.composables.components.showSnackbar
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.BaseViewModel

abstract class BaseActivity : ComponentActivity() {

    abstract val viewModel: BaseViewModel

    @Composable
    abstract fun setContentWithExtras()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            handleSnackbar()
            setContentWithExtras()
        }
    }

    @Composable
    protected fun handleSnackbar() {
        val showSnackbar by viewModel.showSnackBar.observeAsState()
        showSnackbar?.let {
            val scope = rememberCoroutineScope()
            showSnackbar(it, scope, viewModel)
        }
    }
}
