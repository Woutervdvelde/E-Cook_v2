package com.woutervandervelde.e_cook.domain.model

data class IngredientWithQuantity(
    val name: String,
    val unit: MeasurementUnit,
    val quantity: Double
)
