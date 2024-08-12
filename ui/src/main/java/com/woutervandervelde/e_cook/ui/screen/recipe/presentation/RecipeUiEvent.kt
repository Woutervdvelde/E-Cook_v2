package com.woutervandervelde.e_cook.ui.screen.recipe.presentation

import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

interface RecipeUiEvent : BaseUiEvent {
    data object OnBackClick : RecipeUiEvent
    data object OnBookmarkClick : RecipeUiEvent
    data object OnEditClick : RecipeUiEvent
}