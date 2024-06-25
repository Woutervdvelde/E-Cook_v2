package com.woutervandervelde.e_cook.ui.screen.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.Edit
import com.woutervandervelde.e_cook.ui.screen.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

internal fun NavGraphBuilder.homeNavigation(navController: NavController) {
    val navEvent: (HomeNavEvent) -> Unit = { event ->
        when (event) {
            HomeNavEvent.ToEdit -> {
                navController.navigate(Edit)
            }
        }
    }

    composable<HomeRoute>(
        transition = NavigationTransition.FADE(NavigationDuration.FAST),
    ) {
        HomeScreen(navEvent)
    }
}