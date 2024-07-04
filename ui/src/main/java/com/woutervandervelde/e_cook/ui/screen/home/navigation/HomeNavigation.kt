package com.woutervandervelde.e_cook.ui.screen.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksRoute
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditRoute
import com.woutervandervelde.e_cook.ui.screen.home.HomeScreen
import com.woutervandervelde.e_cook.ui.screen.home.viewmodel.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

internal fun NavGraphBuilder.homeNavigation(navController: NavController) {
    val navEvent: (HomeNavEvent) -> Unit = { event ->
        when (event) {
            HomeNavEvent.ToNewRecipe -> {
                navController.navigate(EditRoute())
            }
        }
    }

    composable<HomeRoute>(
        transition = NavigationTransition.FADE(NavigationDuration.FAST),
    ) {
        HomeScreen(hiltViewModel(), navEvent)
    }
}