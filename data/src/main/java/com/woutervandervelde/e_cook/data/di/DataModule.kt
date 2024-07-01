package com.woutervandervelde.e_cook.data.di

import android.content.Context
import com.woutervandervelde.e_cook.data.Database
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): Database =
        Database.create(appContext)

    @Provides
    @Singleton
    fun provideRecipeDao(database: Database) : RecipeDao =
        database.recipeDao()

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeDao: RecipeDao): RecipeRepository =
        RecipeRepository(recipeDao)
}