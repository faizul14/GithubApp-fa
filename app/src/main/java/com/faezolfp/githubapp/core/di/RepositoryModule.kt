package com.faezolfp.githubapp.core.di

import com.faezolfp.githubapp.core.data.ImplRepository
import com.faezolfp.gomovieapp.core.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: ImplRepository): Repository
}