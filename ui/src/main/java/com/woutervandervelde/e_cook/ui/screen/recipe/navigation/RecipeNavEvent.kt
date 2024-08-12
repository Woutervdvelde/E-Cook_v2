package com.woutervandervelde.e_cook.ui.screen.recipe.navigation

import com.woutervandervelde.e_cook.ui.navigation.BaseNavEvent

interface RecipeNavEvent : BaseNavEvent {
    data object Back : RecipeNavEvent
    data class ToRecipeEdit(val recipeId: Long) : RecipeNavEvent
}