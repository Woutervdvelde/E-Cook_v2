package com.woutervandervelde.e_cook.ui.di

import android.content.Context
import com.woutervandervelde.ui.R
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import java.util.Locale

@Module
@ComponentScan("com.woutervandervelde.e_cook.ui")
@Suppress("UtilityClassWithPublicConstructor")
class KoinUiModule {
    companion object {
        val customModule = module {
            single { Locale(get<Context>().getString(R.string.app_locale)) }
        }
    }
}