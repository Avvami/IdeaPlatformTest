package com.personal.ideaplatformtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.ideaplatformtest.core.domain.repository.LocalRepository
import com.personal.ideaplatformtest.core.presentation.GoodsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    private val _goodsState = MutableStateFlow(GoodsState())
    val goodsState: StateFlow<GoodsState> = _goodsState.asStateFlow()

    init {
        viewModelScope.launch {
            localRepository.getGoods().collect { goods ->
                _goodsState.update {
                    it.copy(goods = goods)
                }
            }
        }
    }
}