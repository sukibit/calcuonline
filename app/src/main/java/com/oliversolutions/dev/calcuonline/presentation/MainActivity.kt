package com.oliversolutions.dev.calcuonline.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.google.firebase.FirebaseApp
import com.oliversolutions.dev.calcuonline.R
import com.oliversolutions.dev.calcuonline.presentation.composables.screens.CalculatorScreen
import com.oliversolutions.dev.calcuonline.presentation.theme.CalcuonlineTheme
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val viewModel: CalculatorViewModel by viewModels()

    @Composable
    override fun setContentWithExtras() {
        val selectedCalculator by viewModel.selectedCalculator.observeAsState()
        viewModel.getCalculator()
        selectedCalculator?.let {
            CalcuonlineTheme {
                CalculatorScreen(it, viewModel)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        getString(R.string.app_name)
    }
}
