package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

interface EditUiEvent : BaseUiEvent {
    data object OnBack : EditUiEvent
    data class OnUpdateRecipeName(val name: String) : EditUiEvent
    data class OnUpdateRecipeDescription(val description: String) : EditUiEvent
    data class OnUpdateRecipeTags(val tags: List<Tag>) : EditUiEvent
    data class OnCreateIngredient(val ingredient: Ingredient) : EditUiEvent
    data class OnAddIngredientToRecipe(
        val recipeIngredient: RecipeIngredient
    ) : EditUiEvent

    data class OnAddStepToRecipe(
        val step: String
    ) : EditUiEvent

    data class OnDeleteStepFromRecipe(
        val index: Int
    ) : EditUiEvent

    data object OnSaveRecipe : EditUiEvent
    data object OnDiscardRecipe : EditUiEvent
}