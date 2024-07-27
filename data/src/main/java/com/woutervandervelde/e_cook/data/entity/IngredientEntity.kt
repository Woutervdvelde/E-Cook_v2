package com.woutervandervelde.e_cook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val ingredientId: Long,
    val name: String
)
