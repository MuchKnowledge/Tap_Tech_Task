package com.example.tapYouTT.app.core.di.module

import com.example.tapYouTT.app.core.api.ApiService
import com.example.tapYouTT.app.feature.main.data.repository.PointsRepositoryImpl
import com.example.tapYouTT.app.feature.main.domain.repository.PointsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providePointsRepository(apiService: ApiService): PointsRepository {
        return PointsRepositoryImpl(apiService)
    }
}