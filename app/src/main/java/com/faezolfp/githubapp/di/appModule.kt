package com.faezolfp.githubapp.di

import com.faezolfp.gomovieapp.core.domain.usecase.ImplUseCase
import com.faezolfp.gomovieapp.core.domain.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class appModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(useCase: ImplUseCase): UseCase
}