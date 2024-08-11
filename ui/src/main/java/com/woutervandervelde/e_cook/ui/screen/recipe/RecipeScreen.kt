package com.woutervandervelde.e_cook.ui.screen.recipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.recipe.presentation.RecipeUiEvent
import com.woutervandervelde.e_cook.ui.screen.recipe.presentation.RecipeUiState

@Composable
fun RecipeScreen(
    uiState: RecipeUiState,
    uiEvent: (RecipeUiEvent) -> Unit
) {
    Text(text = "Recipe id: ${uiState.recipe.id}")
}