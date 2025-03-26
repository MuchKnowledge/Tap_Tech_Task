package com.example.modern.di

import com.example.api.GetPointsUseCase
import com.example.api.PointsRepository
import com.example.modern.feature.main.domain.GetPointsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPointsUseCase(repository: PointsRepository): GetPointsUseCase {
        return GetPointsUseCaseImpl(repository)
    }
}