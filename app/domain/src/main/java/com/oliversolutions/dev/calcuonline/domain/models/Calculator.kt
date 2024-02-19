package com.oliversolutions.dev.calcuonline.domain.models

data class Calculator(
    val resId: String,
    var isFavorite: Boolean,
    val isFeatured: Boolean,
    val iconUrl: String,
    val categoryId: Long,
) {
    fun getCalculatorType(): CalculatorType {
        return when (this.resId) {
            "age" -> CalculatorType.AGE
            else -> throw IllegalStateException("Unknown calculator type")
        }
    }
}