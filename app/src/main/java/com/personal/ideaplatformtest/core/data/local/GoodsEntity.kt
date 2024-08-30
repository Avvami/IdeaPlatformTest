package com.personal.ideaplatformtest.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class GoodsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val time: Int,
    val tags: String,
    val amount: Int
)