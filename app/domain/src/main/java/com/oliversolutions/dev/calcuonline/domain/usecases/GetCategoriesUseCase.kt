package com.oliversolutions.dev.calcuonline.domain.usecases

import com.oliversolutions.dev.calcuonline.domain.models.Category
import com.oliversolutions.dev.calcuonline.domain.repositories.CalculatorRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : BaseUseCase<List<Category>, Unit>() {
    override suspend fun execute(params: Unit): List<Category> {
        calculatorRepository.loadData()
        return calculatorRepository.getCategories()
    }
}
