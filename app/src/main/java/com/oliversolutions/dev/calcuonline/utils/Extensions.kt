package com.oliversolutions.dev.calcuonline.utils

import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.models.CategoryData
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CalculatorEntity
import com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models.CategoryEntity

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Long?.orZero(): Long {
    return this ?: 0L
}

fun CalculatorEntity.toData(): CalculatorData {
    return CalculatorData(
        resId = this.resId,
        isFavorite = this.isFavorite,
        isFeatured = this.isFeatured,
        iconUrl = this.iconUrl,
        categoryId = this.categoryId
    )
}

fun CategoryEntity.toData(): CategoryData {
    return CategoryData(
        id = this.id,
        resId = this.resId,
        iconUrl = this.iconUrl,
    )
}

fun CalculatorData.toEntity(): CalculatorEntity {
    return CalculatorEntity(
        resId = this.resId,
        isFavorite = this.isFavorite,
        isFeatured = this.isFeatured,
        iconUrl = this.iconUrl,
        categoryId = this.categoryId
    )
}

fun CategoryData.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id,
        resId = this.resId,
        iconUrl = this.iconUrl,
    )
}
