package com.woutervandervelde.e_cook.ui.screen.books.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.books.BooksScreen
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent
import kotlinx.serialization.Serializable

@Serializable
data object BooksRoute

internal fun NavGraphBuilder.booksNavigation(navController: NavController) {
    val navEvent: (EditNavEvent) -> Unit = { event ->
        when (event) {
            EditNavEvent.Back -> {
                navController.popBackStack()
            }
        }
    }

    composable<BooksRoute>(
        transition = NavigationTransition.FADE(transitionDuration = NavigationDuration.FAST),
    ) {
        BooksScreen(navEvent)
    }
}