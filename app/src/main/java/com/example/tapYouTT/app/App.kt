package com.example.tapYouTT.app

import android.app.Application
import com.example.tapYouTT.app.core.di.component.AppComponent
import com.example.tapYouTT.app.core.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        val appComponent: AppComponent = DaggerAppComponent.create()
    }
}