package com.woutervandervelde.e_cook.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

internal class NavTransition private constructor(
    val enter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?),
    val exit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?),
    val popEnter: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?),
    val popExit: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?),
) {
    companion object {
        val SLIDE = NavTransition(
            enter = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                ) + fadeIn(
                    animationSpec = tween(300),
                    initialAlpha = .5f
                )
            },
            exit = {
                fadeOut(
                    animationSpec = tween(300),
                    targetAlpha = .8f
                )
            },
            popEnter = {
                fadeIn(
                    animationSpec = tween(300),
                    initialAlpha = .8f
                )
            },
            popExit = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                ) + fadeOut(
                    animationSpec = tween(300),
                    targetAlpha = .5f
                )
            }
        )

        val SHEET = NavTransition(
            enter = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(300)
                ) + fadeIn(
                    animationSpec = tween(300),
                    initialAlpha = .5f
                )
            },
            exit = {
                fadeOut(
                    animationSpec = tween(300),
                    targetAlpha = .8f
                )
            },
            popEnter = {
                fadeIn(
                    animationSpec = tween(300),
                    initialAlpha = .8f
                )
            },
            popExit = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(300)
                ) + fadeOut(
                    animationSpec = tween(300),
                    targetAlpha = .5f
                )
            }
        )

        val FADE = NavTransition(
            enter = {
                fadeIn(
                    animationSpec = tween(300),
                    initialAlpha = .5f
                )
            },
            exit = {
                fadeOut(
                    animationSpec = tween(300)
                )
            },
            popEnter = {
                fadeIn(
                    animationSpec = tween(300),
                )
            },
            popExit = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        )

        val NONE = NavTransition(
            enter = {
                fadeIn(
                    animationSpec = tween(1),
                )
            },
            exit = {
                fadeOut(
                    animationSpec = tween(1),

                    )
            },
            popEnter = {
                fadeIn(
                    animationSpec = tween(1),
                )
            },
            popExit = {
                fadeOut(
                    animationSpec = tween(1)
                )
            }
        )
    }
}