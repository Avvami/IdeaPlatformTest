package com.personal.ideaplatformtest.core.domain.models

import java.time.LocalDateTime

data class GoodsInfo(
    val id: Int,
    val name: String,
    val time: LocalDateTime,
    val tags: List<String>,
    val amount: Int
)
