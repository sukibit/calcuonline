package com.oliversolutions.dev.calcuonline.utils.mapper

import com.oliversolutions.dev.calcuonline.domain.models.CalculatorType
import com.oliversolutions.dev.calcuonline.presentation.models.CalculatorInput
import com.oliversolutions.dev.calcuonline.presentation.models.CalculatorOutput

object AgePresentationMapperFactory {
    fun create(calculatorType: CalculatorType): PresentationMapper<CalculatorInput, CalculatorOutput> {
        return when (calculatorType) {
            CalculatorType.AGE -> {
                val inputMapper: InputMapper<CalculatorInput> = AgeInputMapper
                val outputMapper: OutputMapper<CalculatorOutput> = AgeOutputMapper
                val titleMapper: TitleMapper = AgeTitleMapper
                PresentationMapper(inputMapper, outputMapper, titleMapper)
            }
        }
    }
}