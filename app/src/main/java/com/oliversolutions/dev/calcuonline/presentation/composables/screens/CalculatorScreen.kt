package com.oliversolutions.dev.calcuonline.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.oliversolutions.dev.calcuonline.R
import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.presentation.composables.components.SnackbarHostStateSingleton.provideSnackbarHostState
import com.oliversolutions.dev.calcuonline.presentation.composables.components.TopAppBar
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.CalculatorViewModel

@Composable
fun CalculatorScreen(calculator: Calculator, viewModel: CalculatorViewModel) {
    Scaffold(
        topBar = { TopAppBar() },
        snackbarHost = { SnackbarHost(provideSnackbarHostState()) },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(colorResource(id = R.color.light_grey))
        ) {
            CalculatorContent(calculator, viewModel)
        }
    }
}

@Composable
fun CalculatorContent(calculator: Calculator, viewModel: CalculatorViewModel) {
    when (calculator.getCalculatorType()) {
        CalculatorType.AGE -> AgeCalculatorScreen(viewModel, calculator)
    }
}