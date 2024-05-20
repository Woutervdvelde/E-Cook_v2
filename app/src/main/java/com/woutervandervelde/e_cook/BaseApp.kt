package com.woutervandervelde.e_cook

import android.app.Application
import androidx.compose.runtime.Stable
import com.woutervandervelde.e_cook.di.KoinAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(
                KoinAppModule.customModule
            )
        }
    }
}