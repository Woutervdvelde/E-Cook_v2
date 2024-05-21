package com.woutervandervelde.e_cook.ui.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.presentation.feature.home.HomeNavEvent
import com.woutervandervelde.e_cook.ui.ext.composable
import com.woutervandervelde.e_cook.ui.navigation.NavTransition
import com.woutervandervelde.e_cook.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

internal fun NavGraphBuilder.homeNavigation(navController: NavController) {

    val navEvent: (HomeNavEvent) -> Unit = { event ->
        when (event) {
            else -> Unit
        }
    }

    composable<Screen.Home>(
        transition = NavTransition.FADE
    ) {
        HomeRoute(
            viewModel = koinViewModel(),
            navEvent = navEvent
        )
    }
}