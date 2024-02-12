package com.oliversolutions.dev.calcuonline.data.models

import com.oliversolutions.dev.calcuonline.domain.models.Calculator

data class CalculatorData(
    val resId: String,
    val isFavorite: Boolean,
    val isFeatured: Boolean,
    val iconUrl: String,
    val categoryId: Long,
) {
    fun toDomain() = Calculator(
        resId = this.resId,
        isFavorite = this.isFavorite,
        isFeatured = this.isFeatured,
        iconUrl = this.iconUrl,
        categoryId = this.categoryId,
    )
}