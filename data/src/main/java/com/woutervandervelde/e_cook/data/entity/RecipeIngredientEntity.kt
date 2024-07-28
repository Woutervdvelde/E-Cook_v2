package com.woutervandervelde.e_cook.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient

@Entity(
    tableName = "recipe_ingredient",
    foreignKeys = [
        ForeignKey(entity = RecipeEntity::class, parentColumns = ["recipeId"], childColumns = ["recipeId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = IngredientEntity::class, parentColumns = ["name"], childColumns = ["ingredient"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["recipeId"]), Index(value = ["ingredient"])]
)
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val recipeId: Long,
    val ingredient: String,
    val measurementUnit: MeasurementUnit,
    val quantity: Double
) {
    companion object {
        fun fromModel(recipe: Recipe, recipeIngredient: RecipeIngredient) =
            RecipeIngredientEntity(
                0,
                recipe.id,
                recipeIngredient.ingredient.name,
                recipeIngredient.unit,
                recipeIngredient.quantity
            )
    }
}