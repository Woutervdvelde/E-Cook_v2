package com.woutervandervelde.e_cook.domain.model

data class RecipeWithIngredients(
    val recipe: Recipe,
    val ingredients: List<RecipeIngredient>
)