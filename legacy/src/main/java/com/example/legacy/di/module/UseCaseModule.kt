package com.example.legacy.di.module

import com.example.api.GetPointsUseCase
import com.example.api.PointsRepository
import com.example.legacy.feature.main.domain.LegacyGetPointsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideGetPointsUseCase(repository: PointsRepository): GetPointsUseCase {
        return LegacyGetPointsUseCaseImpl(repository)
    }
}