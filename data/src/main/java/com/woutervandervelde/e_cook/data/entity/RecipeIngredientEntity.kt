package com.woutervandervelde.e_cook.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit

@Entity(
    tableName = "recipe_ingredient",
    primaryKeys = ["recipeId", "ingredientId"],
    foreignKeys = [
        ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipeId"]),
        ForeignKey(entity = IngredientEntity::class, parentColumns = ["id"], childColumns = ["ingredientId"])
    ],
    indices = [Index(value = ["recipeId"]), Index(value = ["ingredientId"])]
)
data class RecipeIngredientEntity(
    val recipeId: Int,
    val ingredientId: Int,
    val quantity: Double,
    val unit: MeasurementUnit
)
