package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.data.entity.RecipeIngredientEntity

@Dao
@TypeConverters(Converters::class)
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE recipeId IN (:recipeIds)")
    suspend fun getAllByIds(recipeIds: IntArray): List<RecipeEntity>

    @Insert
    suspend fun insert(recipe: RecipeEntity): Long

    @Insert
    suspend fun insertIngredient(recipeIngredientEntity: RecipeIngredientEntity): Long

    @Insert
    suspend fun inertRecipeIngredient(recipeIngredient: RecipeIngredientEntity)

    @Insert
    suspend fun inertRecipeIngredients(recipeIngredients: List<RecipeIngredientEntity>)

    @Delete
    suspend fun delete(recipe: RecipeEntity)
}