package com.example.legacy

import android.app.Application
import com.example.legacy.di.component.AppComponent
import com.example.legacy.di.component.DaggerAppComponent

class LegacyApp : Application() {

    companion object {
        val appComponent: AppComponent = DaggerAppComponent.create()
    }
}