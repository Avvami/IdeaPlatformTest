package com.personal.ideaplatformtest.core.data.repository

import com.personal.ideaplatformtest.core.data.local.GoodsDao
import com.personal.ideaplatformtest.core.data.local.GoodsEntity
import com.personal.ideaplatformtest.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val goodsDao: GoodsDao
): LocalRepository {
    override fun getGoods(): Flow<List<GoodsEntity>> = goodsDao.getGoods()

    override fun setGoodsAmount(amount: Int) = goodsDao.setGoodsAmount(amount)

    override fun deleteGoods(id: Int) = goodsDao.deleteGoods(id)
}