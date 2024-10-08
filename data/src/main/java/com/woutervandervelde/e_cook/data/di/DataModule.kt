package com.woutervandervelde.e_cook.data.di

import android.content.Context
import com.woutervandervelde.e_cook.data.dao.IngredientDao
import com.woutervandervelde.e_cook.data.database.Database
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.network.InstagramApiService
import com.woutervandervelde.e_cook.data.network.RetrofitClient
import com.woutervandervelde.e_cook.data.network.VideoDownloadService
import com.woutervandervelde.e_cook.data.repository.IngredientRepositoryImpl
import com.woutervandervelde.e_cook.data.repository.InstagramRepositoryImpl
import com.woutervandervelde.e_cook.data.repository.RecipeRepositoryImpl
import com.woutervandervelde.e_cook.data.repository.VideoRepositoryImpl
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.InstagramRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import com.woutervandervelde.e_cook.domain.repository.VideoRepository
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
    fun provideRecipeDao(database: Database): RecipeDao =
        database.recipeDao()

    @Provides
    @Singleton
    fun provideIngredientDao(database: Database): IngredientDao =
        database.ingredientDao()

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeDao: RecipeDao,
        ingredientDao: IngredientDao
    ): RecipeRepository =
        RecipeRepositoryImpl(recipeDao, ingredientDao)

    @Provides
    @Singleton
    fun provideIngredientRepository(ingredientDao: IngredientDao): IngredientRepository =
        IngredientRepositoryImpl(ingredientDao)

    @Provides
    @Singleton
    fun provideInstagramApiService(): InstagramApiService =
        RetrofitClient.getClient().create(InstagramApiService::class.java)

    @Provides
    @Singleton
    fun provideInstagramRepository(apiService: InstagramApiService): InstagramRepository =
        InstagramRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideVideoDownloadService(): VideoDownloadService =
        RetrofitClient.getClient().create(VideoDownloadService::class.java)

    @Provides
    @Singleton
    fun provideVideoRepository(videoDownloadService: VideoDownloadService, @ApplicationContext context: Context): VideoRepository =
        VideoRepositoryImpl(videoDownloadService, context)
}