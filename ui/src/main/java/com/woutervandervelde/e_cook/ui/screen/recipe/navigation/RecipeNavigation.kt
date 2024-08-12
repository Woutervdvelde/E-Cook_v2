package com.woutervandervelde.e_cook.ui.screen.recipe.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditRoute
import com.woutervandervelde.e_cook.ui.screen.recipe.RecipeScreen
import com.woutervandervelde.e_cook.ui.screen.recipe.presentation.RecipeViewModel
import kotlinx.serialization.Serializable

@Serializable
data class RecipeRoute(
    val id: Long = -1
)

internal fun NavGraphBuilder.recipeNavigation(navController: NavController) {
    val navEvent: (RecipeNavEvent) -> Unit = { event ->
        when (event) {
            RecipeNavEvent.Back -> navController.navigateUp()

            is RecipeNavEvent.ToRecipeEdit -> navController.navigate(EditRoute(event.recipeId))
        }
    }

    composable<RecipeRoute>(
        transition = NavigationTransition.FADE()
    ) { backStackEntry ->
        val recipe: RecipeRoute = backStackEntry.toRoute()
        val viewModel: RecipeViewModel = hiltViewModel<RecipeViewModel, RecipeViewModel.Factory>(
            creationCallback = { factory -> factory.create(navEvent, recipe.id) }
        )
        val uiState by viewModel.uiState.collectAsState()
        val uiEvent = viewModel::onUiEvent

        RecipeScreen(uiState, uiEvent)
    }
}