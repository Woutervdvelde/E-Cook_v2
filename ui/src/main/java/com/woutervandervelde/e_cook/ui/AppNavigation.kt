package com.woutervandervelde.e_cook.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.woutervandervelde.e_cook.ui.component.BottomNavigationBar
import com.woutervandervelde.e_cook.ui.component.BottomNavigationItem
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksRoute
import com.woutervandervelde.e_cook.ui.screen.books.navigation.booksNavigation
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeRoute
import com.woutervandervelde.e_cook.ui.screen.home.navigation.homeNavigation
import com.woutervandervelde.e_cook.ui.screen.search.navigation.SearchRoute
import com.woutervandervelde.e_cook.ui.screen.search.navigation.searchNavigation
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceRoute
import com.woutervandervelde.e_cook.ui.screen.source.navigation.sourceNavigation

sealed class Screen<T>(
    val route: T,
    @StringRes val nameResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    data object Home : Screen<HomeRoute>(HomeRoute, R.string.navigation_home, R.drawable.cottage)
    data object Books : Screen<BooksRoute>(BooksRoute, R.string.navigation_books, R.drawable.book)
    data object Search : Screen<SearchRoute>(SearchRoute, R.string.navigation_search, R.drawable.search)
    data object Source : Screen<SourceRoute>(SourceRoute, R.string.navigation_source, R.drawable.bakery_dining)
}

val screenItems = listOf(
    Screen.Home,
    Screen.Books,
    Screen.Search,
    Screen.Source
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentScreen by navController.currentBackStackEntryAsState()

    fun checkIfSelected(screen: Screen<out Any>) =
        currentScreen?.destination?.route?.substringAfterLast(".") ==
                screen.route.javaClass.simpleName.toString()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.safeDrawingPadding()
            ) {
                screenItems.forEach { screen ->
                    BottomNavigationItem(
                        stringResource(id = screen.nameResourceId),
                        painterResource(id = screen.iconResourceId),
                        checkIfSelected(screen),
                        onClick = {
                            if (screen.route == HomeRoute && checkIfSelected(screen)) {
                                navController.popBackStack()
                            }
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { _ ->
        NavHost(
            navController,
            startDestination = HomeRoute
        ) {
            homeNavigation(navController)
            booksNavigation(navController)
            searchNavigation(navController)
            sourceNavigation(navController)
        }
    }
}