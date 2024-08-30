package com.personal.ideaplatformtest.core.di

import com.personal.ideaplatformtest.core.data.repository.LocalRepositoryImpl
import com.personal.ideaplatformtest.core.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}