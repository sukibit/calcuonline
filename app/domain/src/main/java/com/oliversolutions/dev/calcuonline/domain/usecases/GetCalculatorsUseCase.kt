package com.oliversolutions.dev.calcuonline.domain.usecases

import com.oliversolutions.dev.calcuonline.domain.models.Calculator
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import javax.inject.Inject

class GetCalculatorsUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : BaseUseCase<List<Calculator>, Unit>() {
    override suspend fun execute(params: Unit): List<Calculator> {
        calculatorRepository.loadData()
        return calculatorRepository.getCalculators()
    }
}
