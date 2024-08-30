package com.personal.ideaplatformtest.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GoodsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getGoodsDao(): GoodsDao
}