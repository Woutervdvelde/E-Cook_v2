package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.Recipe

interface RecipeRepository  {
    suspend fun getAllRecipe(): List<Recipe>
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
}