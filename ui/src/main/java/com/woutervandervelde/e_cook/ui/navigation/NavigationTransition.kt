package com.woutervandervelde.e_cook.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

enum class NavigationDirection {
    UP, DOWN, LEFT, RIGHT
}

enum class NavigationDuration(
    val durationMillis: Int
) {
    FAST(100),
    NORMAL(300),
    LONG(500)
}

class NavigationTransition(
    val enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = NONE.enterTransition,
    val exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = NONE.exitTransition,
) {
    companion object {
        val NONE = NavigationTransition(
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        )

        /**
         * @param direction The direction the new screen will slide in from
         */
        fun SLIDE(direction: NavigationDirection = NavigationDirection.RIGHT) =
            NavigationTransition(
                enterTransition = {
                    slideIntoContainer(
                        towards = when (direction) {
                            NavigationDirection.UP -> AnimatedContentTransitionScope.SlideDirection.Down
                            NavigationDirection.DOWN -> AnimatedContentTransitionScope.SlideDirection.Up
                            NavigationDirection.LEFT -> AnimatedContentTransitionScope.SlideDirection.Right
                            NavigationDirection.RIGHT -> AnimatedContentTransitionScope.SlideDirection.Left
                        },
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = when (direction) {
                            NavigationDirection.UP -> AnimatedContentTransitionScope.SlideDirection.Up
                            NavigationDirection.DOWN -> AnimatedContentTransitionScope.SlideDirection.Down
                            NavigationDirection.LEFT -> AnimatedContentTransitionScope.SlideDirection.Left
                            NavigationDirection.RIGHT -> AnimatedContentTransitionScope.SlideDirection.Right
                        },
                        animationSpec = tween(300)
                    )
                }
            )

        fun FADE(transitionDuration: NavigationDuration = NavigationDuration.NORMAL) = NavigationTransition(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(transitionDuration.durationMillis),
                    initialAlpha = .5f
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(transitionDuration.durationMillis)
                )
            }
        )
    }
}