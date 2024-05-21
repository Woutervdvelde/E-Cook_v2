package com.woutervandervelde.e_cook

import android.app.Application
import com.woutervandervelde.e_cook.di.KoinAppModule
import com.woutervandervelde.e_cook.presentation.di.KoinPresentationModule
import com.woutervandervelde.e_cook.ui.di.KoinUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.ksp.generated.module
//import org.koin.ksp

abstract class BaseApp : Application(), KoinComponent {
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
//                KoinAppModule().module,
                KoinAppModule.customModule,
                KoinUiModule().module,
                KoinUiModule.customModule,
//                KoinPresentationModule().module,
                KoinPresentationModule.customModule
            )
        }
    }
}