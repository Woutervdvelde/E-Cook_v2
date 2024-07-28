package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.dao.IngredientDao
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.data.entity.RecipeIngredientEntity
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) : RecipeRepository {
    override suspend fun getAllRecipe(): List<Recipe> =
        recipeDao.getAll().map { it.toModel() }

    override suspend fun getRecipeById(id: Long): Recipe? =
        recipeDao.getById(id).toModel()

    override suspend fun getRecipeIngredientsById(id: Long): List<RecipeIngredient> =
        recipeDao.getAllIngredientsForRecipe(id).map {
            val ingredient = ingredientDao.getIngredientByName(it.ingredient)
            RecipeIngredient(
                ingredient!!.toModel(),
                it.measurementUnit,
                it.quantity
            )
        }

    override suspend fun getFullRecipeById(id: Long): RecipeWithIngredients? {
        val recipe = getRecipeById(id)
        val ingredients = getRecipeIngredientsById(id)

        return if (recipe == null) null else RecipeWithIngredients(recipe, ingredients)
    }

    override suspend fun insertRecipe(recipe: Recipe) =
        recipeDao.insert(RecipeEntity.fromModel(recipe))

    override suspend fun insertRecipeIngredient(
        recipe: Recipe,
        recipeIngredient: RecipeIngredient
    ) {
        recipeDao.insertRecipeIngredient(
            RecipeIngredientEntity.fromModel(recipe, recipeIngredient)
        )
    }

    override suspend fun insertRecipeIngredients(
        recipe: Recipe,
        recipeIngredients: List<RecipeIngredient>
    ) {
        recipeDao.insertRecipeIngredients(recipeIngredients.map {
            RecipeIngredientEntity.fromModel(
                recipe, it
            )
        })
    }

    override suspend fun deleteRecipe(recipe: Recipe) =
        recipeDao.delete(RecipeEntity.fromModel(recipe))

    override suspend fun deleteRecipeIngredient(
        recipe: Recipe,
        recipeIngredient: RecipeIngredient
    ) {
        recipeDao.deleteRecipeIngredient(RecipeIngredientEntity.fromModel(recipe, recipeIngredient))
    }
}