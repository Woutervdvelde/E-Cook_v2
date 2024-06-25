package com.woutervandervelde.e_cook.ui.screen.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.woutervandervelde.e_cook.ui.navigation.NavigationDuration
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.navigation.composable
import com.woutervandervelde.e_cook.ui.screen.books.BooksScreen
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksRoute
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent
import com.woutervandervelde.e_cook.ui.screen.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

internal fun NavGraphBuilder.searchNavigation(navController: NavController) {
    val navEvent: (SearchNavEvent) -> Unit = { event ->
        when (event) {
            else -> Unit
        }
    }

    composable<SearchRoute>(
        transition = NavigationTransition.FADE(transitionDuration = NavigationDuration.FAST),
    ) {
        SearchScreen(navEvent)
    }
}