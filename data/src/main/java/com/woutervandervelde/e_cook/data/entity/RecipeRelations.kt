package com.woutervandervelde.e_cook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.woutervandervelde.e_cook.domain.model.IngredientWithQuantity
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients

data class IngredientWithQuantityEntity(
    @Embedded val ingredient: IngredientEntity,
    @ColumnInfo(name = "quantity") val quantity: Double,
    @ColumnInfo(name = "unit") val unit: MeasurementUnit
) {
    fun toModel() = IngredientWithQuantity(
        ingredient.name,
        unit,
        quantity
    )
}

data class RecipeWithIngredientsEntity(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = RecipeIngredientEntity::class,
            parentColumn = "recipeId",
            entityColumn = "ingredientId"
        )
    )
    val ingredients: List<IngredientWithQuantityEntity>
) {
    fun toModel() = RecipeWithIngredients(
        recipe.toModel(), ingredients.map { it.toModel() }
    )
}