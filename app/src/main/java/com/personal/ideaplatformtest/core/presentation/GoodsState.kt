package com.personal.ideaplatformtest.core.presentation

import com.personal.ideaplatformtest.core.data.local.GoodsEntity

data class GoodsState(
    val goods: List<GoodsEntity> = emptyList()
)