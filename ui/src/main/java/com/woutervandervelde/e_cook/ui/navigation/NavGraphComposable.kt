package com.woutervandervelde.e_cook.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import kotlin.reflect.KType

inline fun <reified T : Any> NavGraphBuilder.composable(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    transition: NavigationTransition = NavigationTransition.FADE(NavigationDuration.NORMAL),
    noinline sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards SizeTransform?)? = null,
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = transition.enterTransition,
        exitTransition = transition.exitTransition,
        popEnterTransition = transition.enterTransition,
        popExitTransition = transition.exitTransition,
        sizeTransform = sizeTransform,
        content = content
    )
}