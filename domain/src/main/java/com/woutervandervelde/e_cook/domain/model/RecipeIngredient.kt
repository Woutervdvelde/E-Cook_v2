package com.woutervandervelde.e_cook.domain.model

data class RecipeIngredient(
    val ingredient: Ingredient,
    val unit: MeasurementUnit,
    val quantity: Double
)
