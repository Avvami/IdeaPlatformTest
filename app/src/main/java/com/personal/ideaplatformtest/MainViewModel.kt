package com.personal.ideaplatformtest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.ideaplatformtest.core.domain.repository.LocalRepository
import com.personal.ideaplatformtest.core.presentation.DeleteDialogState
import com.personal.ideaplatformtest.core.presentation.GoodsAmountDialogState
import com.personal.ideaplatformtest.core.presentation.GoodsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    private val _goodsState = MutableStateFlow(GoodsState())
    val goodsState: StateFlow<GoodsState> = _goodsState.asStateFlow()

    private val _goodsSearchQuery = MutableStateFlow("")
    val goodsSearchQuery = _goodsSearchQuery.asStateFlow()

    var deleteDialogState by mutableStateOf(DeleteDialogState())
        private set

    var goodsAmountDialogState by mutableStateOf(GoodsAmountDialogState())
        private set

    init {
        viewModelScope.launch {
            combine(
                localRepository.getGoods(),
                goodsSearchQuery
            ) { goods, query ->
                goods.filter { it.name.contains(query, ignoreCase = true) }
            }.collect { filteredGoods ->
                _goodsState.update {
                    it.copy(goods = filteredGoods)
                }
            }
        }
    }

    fun uiEvent(event: UiEvent) {
        when(event) {
            is UiEvent.SetGoodsSearchQuery -> {
                _goodsSearchQuery.value = event.query
            }
            is UiEvent.ChangeDeleteGoodsDialogState -> {
                deleteDialogState = deleteDialogState.copy(
                    isShown = !deleteDialogState.isShown,
                    goodsId = event.id
                )
            }
            is UiEvent.DeleteGoods -> {
                deleteDialogState.goodsId?.let { id ->
                    viewModelScope.launch {
                        localRepository.deleteGoods(id)
                    }
                }
                deleteDialogState = deleteDialogState.copy(
                    isShown = false,
                    goodsId = null
                )
            }
            is UiEvent.ChangeGoodsAmountDialogState -> {
                goodsAmountDialogState = goodsAmountDialogState.copy(
                    isShown = !goodsAmountDialogState.isShown,
                    goodsId = event.id,
                    amount = event.amount
                )
            }
            is UiEvent.ChangeGoodsAmount -> {
                if (event.amount >= 0) {
                    goodsAmountDialogState = goodsAmountDialogState.copy(
                        amount = event.amount
                    )
                }
            }
            is UiEvent.SetGoodsAmount -> {
                with(goodsAmountDialogState) {
                    if (goodsId != null && amount != null) {
                        viewModelScope.launch {
                            localRepository.setGoodsAmount(goodsId, amount)
                        }
                    }
                }
                goodsAmountDialogState = goodsAmountDialogState.copy(
                    isShown = false,
                    goodsId = null,
                    amount = null
                )
            }
        }
    }
}