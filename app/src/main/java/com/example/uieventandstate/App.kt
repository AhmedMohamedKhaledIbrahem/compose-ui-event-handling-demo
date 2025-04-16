package com.example.uieventandstate

import android.app.Application
import com.example.uieventandstate.auth.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                authModule
            )
        }
    }

}