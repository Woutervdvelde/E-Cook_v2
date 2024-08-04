package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

interface EditUiEvent : BaseUiEvent {
    data object OnBack : EditUiEvent
    data class OnCreateIngredient(val ingredient: Ingredient) : EditUiEvent
    data class OnAddIngredientToRecipe(
        val recipe: Recipe,
        val recipeIngredient: RecipeIngredient
    ) : EditUiEvent

    data object OnSaveRecipe : EditUiEvent
    data object OnDiscardRecipe : EditUiEvent
}