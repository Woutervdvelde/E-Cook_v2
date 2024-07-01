package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.woutervandervelde.e_cook.data.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipe WHERE id IN (:recipeIds)")
    fun getAllByIds(recipeIds: IntArray): List<Recipe>

    @Insert
    fun insert(vararg recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}