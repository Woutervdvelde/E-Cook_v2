package com.example.e_cook

import android.app.Application
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules()
        }
    }
}