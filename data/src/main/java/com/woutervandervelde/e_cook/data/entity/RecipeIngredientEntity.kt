package com.woutervandervelde.e_cook.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit

@Entity(
    tableName = "recipe_ingredient",
    foreignKeys = [
        ForeignKey(entity = RecipeEntity::class, parentColumns = ["recipeId"], childColumns = ["recipeId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = IngredientEntity::class, parentColumns = ["ingredientId"], childColumns = ["ingredientId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["recipeId"]), Index(value = ["ingredientId"])]
)
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val recipeId: Long,
    val ingredientId: Long,
    val measurementUnit: MeasurementUnit,
    val amount: Double
)