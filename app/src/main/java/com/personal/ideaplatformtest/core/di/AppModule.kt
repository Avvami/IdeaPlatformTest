package com.personal.ideaplatformtest.core.di

import android.content.Context
import androidx.room.Room
import com.personal.ideaplatformtest.core.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Goods.db"
        ).createFromAsset("database/data.db").build()
    }

    @Singleton
    @Provides
    fun providePreferencesDao(db: AppDatabase) = db.getGoodsDao()
}