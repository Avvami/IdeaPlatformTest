package com.personal.ideaplatformtest.core.domain.repository

import com.personal.ideaplatformtest.core.data.local.GoodsEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getGoods(): Flow<List<GoodsEntity>>

    fun setGoodsAmount(amount: Int)

    fun deleteGoods(id: Int)
}