package com.woutervandervelde.e_cook.ui.ext

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable as cmp
import com.woutervandervelde.e_cook.ui.navigation.NavTransition
import com.woutervandervelde.e_cook.ui.navigation.Screen

internal inline fun <reified T : Screen> NavGraphBuilder.composable(
    transition: NavTransition? = null,
    noinline content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    cmp<T>(
        enterTransition = transition?.enter,
        exitTransition = transition?.exit,
        popEnterTransition = transition?.popEnter,
        popExitTransition = transition?.popExit,
        content = content
    )
}