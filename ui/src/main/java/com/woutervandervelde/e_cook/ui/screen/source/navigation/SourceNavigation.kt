package com.woutervandervelde.e_cook.ui.screen.source.navigation

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeRoute
import com.woutervandervelde.e_cook.ui.screen.recipe.navigation.RecipeRoute
import com.woutervandervelde.e_cook.ui.screen.source.SourceScreen
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceViewModel
import com.woutervandervelde.e_cook.ui.theme.Size32
import kotlinx.serialization.Serializable

@Serializable
data class SourceRoute(
    val reelId: String = ""
)

fun NavGraphBuilder.sourceNavigation(navController: NavController) {
    val navEvent: (SourceNavEvent) -> Unit = { event ->
        when (event) {
            is SourceNavEvent.ToRecipe ->
                navController.navigate(RecipeRoute(event.recipeId))

            else -> Unit
        }
    }

    composable<SourceRoute>(
        transition = NavigationTransition.FADE(transitionDuration = NavigationDuration.FAST),
        deepLinks = listOf(
            navDeepLink<SourceRoute>(
                basePath = "share",
                deepLinkBuilder = {
                    action = Intent.ACTION_SEND
                }
            )
        )
    ) { backstackEntry ->
        var data: String? = null
        val intent = (backstackEntry.arguments?.get(NavController.KEY_DEEP_LINK_INTENT) as? Intent)
        if (intent?.action == Intent.ACTION_SEND) {
            data = intent.getStringExtra(Intent.EXTRA_TEXT)
        }

        val viewModel: SourceViewModel = hiltViewModel<SourceViewModel, SourceViewModel.Factory>(
            creationCallback = { factory -> factory.create(navEvent, data) }
        )
        val uiState by viewModel.uiState.collectAsState()
        val uiEvent = viewModel::onUiEvent

        SourceScreen(uiState, uiEvent)
    }
}