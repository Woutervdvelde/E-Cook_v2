package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.dao.IngredientDao
import com.woutervandervelde.e_cook.data.entity.IngredientEntity
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
): IngredientRepository {
    override suspend fun getAllIngredients(): List<Ingredient> =
        ingredientDao.getAllIngredients().map { it.toModel() }

    override suspend fun getIngredientByName(name: String): Ingredient? =
        ingredientDao.getIngredientByName(name)?.toModel()

    override suspend fun insertIngredient(ingredient: Ingredient) {
        ingredientDao.insert(IngredientEntity.fromModel(ingredient))
    }

    override suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.delete(IngredientEntity.fromModel(ingredient))
    }
}