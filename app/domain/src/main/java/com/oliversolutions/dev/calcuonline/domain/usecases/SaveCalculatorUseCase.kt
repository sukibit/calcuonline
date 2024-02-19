package com.oliversolutions.dev.calcuonline.domain.usecases

import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import javax.inject.Inject

class SaveCalculatorUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : BaseUseCase<Unit, Calculator>() {
    override suspend fun execute(params: Calculator) {
        return calculatorRepository.saveCalculator(params)
    }
}