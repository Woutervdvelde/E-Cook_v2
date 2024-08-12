package com.woutervandervelde.e_cook.ui.screen.edit.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.edit.EditScreen
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditViewModel
import kotlinx.serialization.Serializable

@Serializable
data class EditRoute(
    val id: Long = -1
)

internal fun NavGraphBuilder.editNavigation(navController: NavController) {
    val navEvent: (EditNavEvent) -> Unit = { event ->
        when (event) {
            EditNavEvent.Back -> {
                navController.navigateUp()
            }
        }
    }

    composable<EditRoute>(
        transition = NavigationTransition.FADE(),
    ) {backStackEntry ->
        val edit: EditRoute = backStackEntry.toRoute()

        val viewModel: EditViewModel = hiltViewModel<EditViewModel, EditViewModel.Factory>(
            creationCallback = { factory -> factory.create(navEvent, edit.id) }
        )
        val uiState by viewModel.uiState.collectAsState()
        val uiEvent = viewModel::onUiEvent

        EditScreen(uiState, uiEvent)
    }
}