package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient

interface RecipeRepository  {
    suspend fun getAllRecipe(): List<Recipe>
    suspend fun insertRecipe(recipe: Recipe): Long
    suspend fun insertRecipeIngredient(recipe: Recipe, recipeIngredient: RecipeIngredient)
    suspend fun insertRecipeIngredients(recipe: Recipe, recipeIngredient: List<RecipeIngredient>)
    suspend fun deleteRecipe(recipe: Recipe)
}