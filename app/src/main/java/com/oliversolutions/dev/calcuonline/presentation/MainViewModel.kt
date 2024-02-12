package com.oliversolutions.dev.calcuonline.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCalculatorResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetCalculatorResultUseCase,
) : ViewModel() {

    fun loadData() {
        viewModelScope.launch {
            useCase(
                CalculatorQuery(
                    calculatorType = CalculatorType.BMI,
                    input = "{\"age\":\"28\",\"height\":{\"value\":\"188\",\"unit\":\"cm\"},\"weight\":{\"value\":\"81\",\"unit\":\"kg\"}}"
                )
            ).fold(
                onSuccess = { result ->
                    Log.i("MainViewModel", result)
                },
                onFailure = { exception ->
                    Log.e("MainViewModel", "Failed to load data: ${exception.message}", exception)
                }
            )
        }
    }
}
