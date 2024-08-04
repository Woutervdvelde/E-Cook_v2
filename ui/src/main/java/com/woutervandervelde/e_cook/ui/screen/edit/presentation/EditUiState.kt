package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class EditUiState(
    val recipe: RecipeWithIngredients = RecipeWithIngredients.Empty(),
    val allIngredients: List<Ingredient> = listOf(),
    val recipeIngredients: MutableList<RecipeIngredient> = mutableListOf(),
    val recipeSteps: MutableList<String> = mutableListOf()
) : BaseUiState()
