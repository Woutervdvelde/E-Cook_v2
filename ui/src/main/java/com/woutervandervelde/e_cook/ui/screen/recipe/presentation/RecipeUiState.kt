package com.woutervandervelde.e_cook.ui.screen.recipe.presentation

import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class RecipeUiState(
    val recipe: RecipeWithIngredients = RecipeWithIngredients.Empty()
) : BaseUiState()