package com.personal.ideaplatformtest.core.presentation

data class GoodsAmountDialogState(
    val isShown: Boolean = false,
    val goodsId: Int? = null,
    val amount: Int? = null
)
