package com.personal.ideaplatformtest.core.data.repository

import com.personal.ideaplatformtest.core.data.local.GoodsDao
import com.personal.ideaplatformtest.core.data.mappers.toGoodsInfo
import com.personal.ideaplatformtest.core.domain.models.GoodsInfo
import com.personal.ideaplatformtest.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val goodsDao: GoodsDao
): LocalRepository {
    override fun getGoods(): Flow<List<GoodsInfo>> = goodsDao.getGoods().map { flowList -> flowList.map { it.toGoodsInfo() } }

    override suspend fun setGoodsAmount(id: Int, amount: Int) = goodsDao.setGoodsAmount(id, amount)

    override suspend fun deleteGoods(id: Int) = goodsDao.deleteGoods(id)
}