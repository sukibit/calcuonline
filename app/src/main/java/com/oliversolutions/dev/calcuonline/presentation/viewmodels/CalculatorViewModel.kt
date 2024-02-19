package com.oliversolutions.dev.calcuonline.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCalculatorResultUseCase
import com.oliversolutions.dev.calcuonline.domain.usecases.GetCalculatorsUseCase
import com.oliversolutions.dev.calcuonline.domain.usecases.SaveCalculatorUseCase
import com.oliversolutions.dev.calcuonline.presentation.models.CalculatorInput
import com.oliversolutions.dev.calcuonline.utils.mapper.AgePresentationMapperFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    application: Application,
    private val useCase: GetCalculatorResultUseCase,
    private val saveCalculatorUseCase: SaveCalculatorUseCase,
    private val getCalculatorsUseCase: GetCalculatorsUseCase,
) : BaseViewModel(application) {

    private val _calculatorOutput = MutableLiveData<String>()
    val calculatorOutput: LiveData<String> = _calculatorOutput

    private val _selectedCalculator = MutableLiveData<Calculator>()
    val selectedCalculator: LiveData<Calculator> = _selectedCalculator

    fun getCalculatorData(
        calculator: Calculator,
        input: String,
        output: String
    ): Map<String, Any?> {
        val presentationMapper = AgePresentationMapperFactory.create(calculator.getCalculatorType())
        val calculatorInput = presentationMapper.getInput(input)
        val calculatorOutput = presentationMapper.getOutput(output)
        val calculatorTitle = presentationMapper.getTitleRes()
        return mapOf(
            "input" to calculatorInput,
            "output" to calculatorOutput,
            "title" to calculatorTitle,
        )
    }

    fun saveCalculator(calculator: Calculator) {
        viewModelScope.launch {
            saveCalculatorUseCase(calculator).fold(
                onSuccess = {
                    _selectedCalculator.value = calculator
                },
                onFailure = {
                    resolveError(it)
                }
            )
        }
    }

    fun getCalculator() {
        viewModelScope.launch {
            getCalculatorsUseCase().fold(
                onSuccess = { result ->
                    _selectedCalculator.value = result[0]
                },
                onFailure = {
                    resolveError(it)
                }
            )
        }
    }

    fun load(input: CalculatorInput?, calculatorType: CalculatorType) {
        viewModelScope.launch {
            useCase(
                CalculatorQuery(
                    calculatorType = calculatorType,
                    input = Gson().toJson(input)
                )
            ).fold(
                onSuccess = { result ->
                    _calculatorOutput.value = result
                },
                onFailure = {
                    resolveError(it)
                }
            )
        }
    }
}
