package com.example.tapYouTT.app.core.di.module

import com.example.tapYouTT.app.feature.main.domain.repository.PointsRepository
import com.example.tapYouTT.app.feature.main.domain.use_case.GetPointsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPointsUseCase(repository: PointsRepository): GetPointsUseCase {
        return GetPointsUseCase(repository)
    }
}