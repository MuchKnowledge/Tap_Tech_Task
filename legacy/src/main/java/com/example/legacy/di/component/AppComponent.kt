package com.example.legacy.di.component

import com.example.legacy.di.module.ApiModule
import com.example.legacy.di.module.AppModule
import com.example.legacy.di.module.NetworkModule
import com.example.legacy.di.module.RepositoryModule
import com.example.legacy.di.module.UseCaseModule
import com.example.legacy.feature.main.presentation.LegacyMainActivity
import com.example.legacy.feature.result.LegacyResultActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
    ]
)
interface AppComponent {
    fun inject(activity: LegacyMainActivity)
    fun inject(activity: LegacyResultActivity)
}