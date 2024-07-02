package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor (private val recipeDao: RecipeDao) : RecipeRepository {
    override suspend fun getAllRecipe(): List<Recipe> =
        recipeDao.getAll().map { it.toModel() }

    override suspend fun insertRecipe(recipe: Recipe) =
        recipeDao.insert(RecipeEntity.fromModel(recipe))

    override suspend fun deleteRecipe(recipe: Recipe) =
        recipeDao.delete(RecipeEntity.fromModel(recipe))
}