package com.personal.ideaplatformtest

sealed interface UiEvent {
    data class SetGoodsSearchQuery(val query: String): UiEvent
    data class ChangeDeleteGoodsDialogState(val id: Int? = null): UiEvent
    data object DeleteGoods: UiEvent
    data class ChangeGoodsAmountDialogState(val id: Int? = null, val amount: Int? = null): UiEvent
    data class ChangeGoodsAmount(val amount: Int): UiEvent
    data object SetGoodsAmount: UiEvent
}