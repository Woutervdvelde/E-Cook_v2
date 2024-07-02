package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.RecipeEntity
import com.woutervandervelde.e_cook.domain.model.Recipe

@Dao
@TypeConverters(Converters::class)
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE id IN (:recipeIds)")
    fun getAllByIds(recipeIds: IntArray): List<RecipeEntity>

    @Insert
    fun insert(vararg recipe: RecipeEntity)

    @Delete
    fun delete(recipe: RecipeEntity)
}