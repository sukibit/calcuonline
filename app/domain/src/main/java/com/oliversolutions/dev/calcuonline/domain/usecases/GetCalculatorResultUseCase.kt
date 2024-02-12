package com.oliversolutions.dev.calcuonline.domain.usecases

import com.oliversolutions.dev.calcuonline.domain.models.CalculatorQuery
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import javax.inject.Inject

class GetCalculatorResultUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : BaseUseCase<String, CalculatorQuery>() {
    override suspend fun execute(params: CalculatorQuery): String {
        return calculatorRepository.getCalculatorResult(params)
    }
}
