package com.woutervandervelde.e_cook.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.woutervandervelde.e_cook")
class KoinAppModule {
    companion object {
        val customModule = module {

        }
    }
}