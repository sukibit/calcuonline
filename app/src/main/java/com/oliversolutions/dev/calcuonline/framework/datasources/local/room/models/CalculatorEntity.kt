package com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "calculators",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class CalculatorEntity(
    @PrimaryKey val resId: String,
    val isFavorite: Boolean,
    val isFeatured: Boolean,
    val iconUrl: String,
    val categoryId: Long
)