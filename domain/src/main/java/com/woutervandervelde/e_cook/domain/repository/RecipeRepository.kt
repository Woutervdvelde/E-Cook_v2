package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients

interface RecipeRepository  {
    suspend fun getAllRecipe(): List<Recipe>
    suspend fun getRecipeById(id: Long): Recipe?
    suspend fun getRecipeIngredientsById(id: Long): List<RecipeIngredient>
    suspend fun getFullRecipeById(id: Long): RecipeWithIngredients?
    suspend fun insertRecipe(recipe: Recipe): Long
    suspend fun insertRecipeIngredient(recipe: Recipe, recipeIngredient: RecipeIngredient)
    suspend fun insertRecipeIngredients(recipe: Recipe, recipeIngredients: List<RecipeIngredient>)
    suspend fun updateRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun deleteFullRecipe(recipe: RecipeWithIngredients)
    suspend fun deleteRecipeIngredient(recipe: Recipe, recipeIngredient: RecipeIngredient)
}