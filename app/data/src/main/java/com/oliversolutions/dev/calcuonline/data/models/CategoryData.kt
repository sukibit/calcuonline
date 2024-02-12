package com.oliversolutions.dev.calcuonline.data.models

import com.oliversolutions.dev.calcuonline.domain.models.Category

data class CategoryData(
    val id: Long,
    val iconUrl: String,
    val resId: String,
) {
    fun toDomain() = Category(
        id = this.id,
        iconUrl = this.iconUrl,
        resId = this.resId,
    )
}