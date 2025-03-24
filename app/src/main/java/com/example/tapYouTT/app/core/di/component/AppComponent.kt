package com.example.tapYouTT.app.core.di.component

import com.example.tapYouTT.app.core.di.module.ApiModule
import com.example.tapYouTT.app.core.di.module.AppModule
import com.example.tapYouTT.app.core.di.module.NetworkModule
import com.example.tapYouTT.app.core.di.module.RepositoryModule
import com.example.tapYouTT.app.core.di.module.UseCaseModule
import com.example.tapYouTT.app.feature.main.presentation.MainActivity
import com.example.tapYouTT.app.feature.result.presentation.ResultActivity
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
    fun inject(activity: MainActivity)
    fun inject(activity: ResultActivity)
}