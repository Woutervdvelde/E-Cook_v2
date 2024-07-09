package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Quantity
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients

interface RecipeRepository  {
    suspend fun getAllRecipe(): List<Recipe>
    suspend fun getRecipeWithIngredients(recipeId: Int): RecipeWithIngredients
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun insertRecipeWithIngredients(recipe: Recipe, ingredients: List<Pair<Ingredient, Quantity>>)
    suspend fun deleteRecipe(recipe: Recipe)
}