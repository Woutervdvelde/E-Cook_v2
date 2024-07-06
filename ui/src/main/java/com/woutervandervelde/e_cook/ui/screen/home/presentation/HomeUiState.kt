package com.woutervandervelde.e_cook.ui.screen.home.presentation

import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class HomeUiState(
    var recipes: List<Recipe> = listOf()
) : BaseUiState()