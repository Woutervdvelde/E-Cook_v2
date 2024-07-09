package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.data.entity.RecipeIngredientEntity
import com.woutervandervelde.e_cook.data.entity.RecipeWithIngredientsEntity

@Dao
@TypeConverters(Converters::class)
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE id IN (:recipeIds)")
    suspend fun getAllByIds(recipeIds: IntArray): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeWithIngredients(recipeId: Int): RecipeWithIngredientsEntity

    @Insert
    suspend fun insert(vararg recipe: RecipeEntity)

    @Insert
    suspend fun inertRecipeIngredient(recipeIngredient: RecipeIngredientEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)
}