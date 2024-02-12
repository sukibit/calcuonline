package com.oliversolutions.dev.calcuonline.framework.datasources.local.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Long,
    val iconUrl: String,
    val resId: String
)
