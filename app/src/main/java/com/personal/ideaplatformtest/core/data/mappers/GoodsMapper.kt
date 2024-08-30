package com.personal.ideaplatformtest.core.data.mappers

import com.personal.ideaplatformtest.core.data.local.GoodsEntity
import com.personal.ideaplatformtest.core.domain.models.GoodsInfo
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun GoodsEntity.toGoodsInfo(): GoodsInfo {
    val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault())
    val tagsList = tags.removeSurrounding("[", "]").split(",").map { it.trim().removeSurrounding("\"") }
    return GoodsInfo(
        id = id,
        name = name,
        time = localDateTime,
        tags = tagsList,
        amount = amount
    )
}