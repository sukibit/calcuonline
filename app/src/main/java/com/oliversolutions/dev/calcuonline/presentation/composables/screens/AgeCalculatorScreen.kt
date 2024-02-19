package com.oliversolutions.dev.calcuonline.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliversolutions.dev.calcuonline.R
import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.presentation.composables.components.DatePickerDialog
import com.oliversolutions.dev.calcuonline.presentation.composables.components.SecondaryAppBar
import com.oliversolutions.dev.calcuonline.presentation.models.AgeCalculatorInput
import com.oliversolutions.dev.calcuonline.presentation.models.AgeCalculatorOutput
import com.oliversolutions.dev.calcuonline.presentation.viewmodels.CalculatorViewModel

@Composable
fun AgeCalculatorScreen(viewModel: CalculatorViewModel, calculator: Calculator) {
    var date by remember {
        mutableStateOf("Select a date")
    }
    val output by viewModel.calculatorOutput.observeAsState()
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var enableButton by remember {
        mutableStateOf(false)
    }

    val calculatorData = viewModel.getCalculatorData(calculator, date, output.orEmpty())
    val calculatorTitle = LocalContext.current.getString((calculatorData["title"] as? Int) ?: 0)
    val calculatorAgeInput = calculatorData["input"] as? AgeCalculatorInput
    val calculatorAgeOutput = calculatorData["output"] as? AgeCalculatorOutput

    SecondaryAppBar(calculatorTitle, viewModel)
    TextField(value = date,
        onValueChange = {},
        placeholder = { Text(stringResource(R.string.age_calculator_select_date)) },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                showDatePicker = true
            })

    Button(
        enabled = enableButton,
        onClick = {
            viewModel.load(calculatorAgeInput, calculator.getCalculatorType())
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = stringResource(R.string.age_calculator_button))
    }

    calculatorAgeOutput?.let {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = it.daysPassed.toString() + " " + stringResource(R.string.age_calculator_days),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray),

                )
                Text(
                    text = it.hoursPassed.toString() + " " + stringResource(R.string.age_calculator_hours),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray),

                )
                Text(
                    text = it.minutesPassed.toString() + " " + stringResource(R.string.age_calculator_minutes),
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                )
            }
        }
    }
    if (showDatePicker) {
        DatePickerDialog(onDateSelected = {
            enableButton = true
            date = it
        }, onDismiss = {
            showDatePicker = false
        })
    }
}