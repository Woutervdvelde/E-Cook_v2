package com.woutervandervelde.e_cook.ui.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.woutervandervelde.e_cook.ui")
class KoinUiModule {
    companion object {
        val customModule = module {

        }
    }
}