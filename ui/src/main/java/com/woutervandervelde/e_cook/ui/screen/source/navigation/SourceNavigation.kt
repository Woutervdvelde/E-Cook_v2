package com.woutervandervelde.e_cook.ui.screen.source.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.source.SourceScreen
import kotlinx.serialization.Serializable

@Serializable
data object SourceRoute

fun NavGraphBuilder.sourceNavigation(navController: NavController) {
    val navEvent: (SourceNavEvent) -> Unit = { event ->
        when (event) {
            else -> Unit
        }
    }

    composable<SourceRoute>(
        transition = NavigationTransition.FADE(transitionDuration = NavigationDuration.FAST),
    ) {
        SourceScreen(navEvent)
    }
}