package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class EditUiState(
    val allIngredients: List<Ingredient> = listOf(),
    val recipeName: String = "",
    val recipeDescription: String = "",
    val recipeTags: List<Tag> = listOf(),
    val recipeIngredients: List<RecipeIngredient> = listOf(),
    val recipeSteps: List<String> = listOf()
) : BaseUiState()
