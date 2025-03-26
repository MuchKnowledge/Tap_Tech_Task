package com.example.legacy.di.module

import com.example.api.ApiService
import com.example.api.PointsRepository
import com.example.legacy.feature.main.data.LegacyPointsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providePointsRepository(apiService: ApiService): PointsRepository {
        return LegacyPointsRepositoryImpl(apiService)
    }
}