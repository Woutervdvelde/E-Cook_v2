package com.woutervandervelde.e_cook.presentation.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.woutervandervelde.e_cook.presentation")
class KoinPresentationModule {
    companion object {
        val customModule = module {

        }
    }
}