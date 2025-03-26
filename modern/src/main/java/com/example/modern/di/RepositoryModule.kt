package com.example.modern.di

import com.example.api.ApiService
import com.example.api.PointsRepository
import com.example.modern.feature.main.data.PointsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePointsRepository(apiService: ApiService): PointsRepository {
        return PointsRepositoryImpl(apiService)
    }
}