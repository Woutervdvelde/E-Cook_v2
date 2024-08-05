package com.woutervandervelde.e_cook.ui.screen.home.presentation

import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

sealed interface HomeUiEvent : BaseUiEvent {
    data object OnAddRecipeClick : HomeUiEvent
    data object OnRandomRecipeClick : HomeUiEvent
    data class OnEditRecipe(val id: Long) : HomeUiEvent
}