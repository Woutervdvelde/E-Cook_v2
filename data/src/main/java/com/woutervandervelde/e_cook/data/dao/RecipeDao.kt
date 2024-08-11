package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.data.entity.RecipeIngredientEntity
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient

@Dao
@TypeConverters(Converters::class)
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getById(id: Long): RecipeEntity?

    @Query("SELECT * FROM recipe WHERE recipeId IN (:recipeIds)")
    suspend fun getAllByIds(recipeIds: IntArray): List<RecipeEntity?>

    @Query("SELECT * FROM recipe_ingredient WHERE recipeId = :id")
    suspend fun getAllIngredientsForRecipe(id: Long): List<RecipeIngredientEntity>

    @Insert
    suspend fun insert(recipe: RecipeEntity): Long

    @Insert
    suspend fun insertIngredient(recipeIngredientEntity: RecipeIngredientEntity): Long

    @Insert
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredientEntity)

    @Insert
    suspend fun insertRecipeIngredients(recipeIngredients: List<RecipeIngredientEntity>)

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredientEntity)
}