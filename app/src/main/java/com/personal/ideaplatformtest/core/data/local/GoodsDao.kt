package com.personal.ideaplatformtest.core.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GoodsDao {

    @Query("SELECT * FROM item")
    fun getGoods(): Flow<List<GoodsEntity>>

    @Query("UPDATE item SET amount = :amount")
    fun setGoodsAmount(amount: Int)

    @Query("DELETE FROM item WHERE id = :id")
    fun deleteGoods(id: Int)
}