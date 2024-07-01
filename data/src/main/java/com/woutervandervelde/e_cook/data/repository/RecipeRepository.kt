package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.model.Recipe
import javax.inject.Inject

class RecipeRepository @Inject constructor (private val recipeDao: RecipeDao) {
    suspend fun getAllRecipe(): List<Recipe> =
        recipeDao.getAll()

    suspend fun insertRecipe(recipe: Recipe) =
        recipeDao.insert(recipe)

    suspend fun deleteRecipe(recipe: Recipe) =
        recipeDao.delete(recipe)
}