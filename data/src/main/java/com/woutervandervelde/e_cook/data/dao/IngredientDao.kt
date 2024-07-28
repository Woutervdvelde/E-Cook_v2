package com.woutervandervelde.e_cook.data.dao

import androidx.room.Dao
import androidx.room.Delete
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
    suspend fun getIngredientById(id: Long): IngredientEntity?

    @Query("SELECT * FROM ingredient WHERE name = :name")
    suspend fun getIngredientByName(name: String): IngredientEntity?

    @Insert
    suspend fun insert(ingredient: IngredientEntity): Long

    @Delete
    suspend fun delete(ingredient: IngredientEntity)
}