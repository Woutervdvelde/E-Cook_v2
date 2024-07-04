package com.woutervandervelde.e_cook.ui.screen.edit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDirection
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.edit.EditScreen
import kotlinx.serialization.Serializable

@Serializable
data class EditRoute(
    val id: String? = null
)

internal fun NavGraphBuilder.editNavigation(navController: NavController) {
    val navEvent: (EditNavEvent) -> Unit = { event ->
        when (event) {
            EditNavEvent.Back -> {
                navController.popBackStack()
            }
        }
    }

    composable<EditRoute>(
        transition = NavigationTransition.FADE(),
    ) {
        EditScreen(navEvent)
    }
}