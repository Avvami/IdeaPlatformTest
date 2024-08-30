package com.personal.ideaplatformtest.core.domain.repository

import com.personal.ideaplatformtest.core.domain.models.GoodsInfo
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getGoods(): Flow<List<GoodsInfo>>

    suspend fun setGoodsAmount(id: Int, amount: Int)

    suspend fun deleteGoods(id: Int)
}