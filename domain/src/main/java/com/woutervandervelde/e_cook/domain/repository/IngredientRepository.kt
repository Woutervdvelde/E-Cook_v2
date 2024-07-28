package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.Ingredient

interface IngredientRepository {
    suspend fun getIngredientById(id: Long): Ingredient?
    suspend fun getIngredientByName(name: String): Ingredient?
    suspend fun insertIngredient(ingredient: Ingredient): Long
    suspend fun deleteIngredient(ingredient: Ingredient)
}