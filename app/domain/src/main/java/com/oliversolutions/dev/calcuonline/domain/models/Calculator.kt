package com.oliversolutions.dev.calcuonline.domain.models

data class Calculator(
    val resId: String,
    val isFavorite: Boolean,
    val isFeatured: Boolean,
    val iconUrl: String,
    val categoryId: Long,
)