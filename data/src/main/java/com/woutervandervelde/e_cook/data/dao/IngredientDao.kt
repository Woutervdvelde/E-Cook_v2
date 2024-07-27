package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.IngredientEntity

@Dao
@TypeConverters(Converters::class)
interface IngredientDao {
    @Query("SELECT * FROM ingredient")
    suspend fun getAllIngredients(): List<IngredientEntity>

    @Query("SELECT * FROM ingredient WHERE ingredientId = :id")
    suspend fun getIngredientById(id: Long): IngredientEntity

    @Insert
    suspend fun insert(ingredient: IngredientEntity): Long
}