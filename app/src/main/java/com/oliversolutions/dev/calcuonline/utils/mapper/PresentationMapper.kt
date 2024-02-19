package com.oliversolutions.dev.calcuonline.utils.mapper

class PresentationMapper<Input, Output>(
    private val inputMapper: InputMapper<Input>,
    private val outputMapper: OutputMapper<Output>,
    private val titleMapper: TitleMapper
) {
    fun getOutput(output: String): Output? {
        return outputMapper.map(output)
    }

    fun getInput(input: String): Input? {
        return inputMapper.map(input)
    }

    fun getTitleRes(): Int? {
        return titleMapper.getTitleRes()
    }
}