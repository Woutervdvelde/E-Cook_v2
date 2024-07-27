package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.dao.IngredientDao
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) : RecipeRepository {
    override suspend fun getAllRecipe(): List<Recipe> =
        recipeDao.getAll().map { it.toModel() }

    override suspend fun insertRecipe(recipe: Recipe) =
        recipeDao.insert(RecipeEntity.fromModel(recipe))

    override suspend fun insertRecipeIngredient(
        recipe: Recipe,
        recipeIngredient: RecipeIngredient
    ) {
        //TODO("Not yet implemented")
    }

    override suspend fun insertRecipeIngredients(
        recipe: Recipe,
        recipeIngredient: List<RecipeIngredient>
    ) {
        //TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipe: Recipe) =
        recipeDao.delete(RecipeEntity.fromModel(recipe))
}