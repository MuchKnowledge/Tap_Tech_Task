package com.example.tapYouTT.app

import android.app.Application
import com.example.tapYouTT.app.core.di.component.AppComponent
import com.example.tapYouTT.app.core.di.component.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone
import timber.log.Timber

class App : Application() {

    companion object {
        val appComponent: AppComponent = DaggerAppComponent.create()

        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}