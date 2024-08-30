package com.personal.ideaplatformtest.core.presentation

import com.personal.ideaplatformtest.core.domain.models.GoodsInfo

data class GoodsState(
    val goods: List<GoodsInfo> = emptyList()
)