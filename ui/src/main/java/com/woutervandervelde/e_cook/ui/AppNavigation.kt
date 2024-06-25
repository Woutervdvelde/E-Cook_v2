package com.woutervandervelde.e_cook.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.woutervandervelde.e_cook.ui.component.BottomNavigationBar
import com.woutervandervelde.e_cook.ui.component.BottomNavigationItem
import com.woutervandervelde.e_cook.ui.navigation.NavigationTransition
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksRoute
import com.woutervandervelde.e_cook.ui.screen.books.navigation.booksNavigation
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.editNavigation
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeRoute
import com.woutervandervelde.e_cook.ui.screen.home.navigation.homeNavigation
import com.woutervdvelde.e_cook.ui.R
import kotlin.reflect.typeOf

sealed class Screen<T>(
    val route: T,
    @StringRes val nameResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    data object Home : Screen<HomeRoute>(HomeRoute, R.string.navigation_home, R.drawable.cottage)
    data object Books : Screen<BooksRoute>(BooksRoute, R.string.navigation_books, R.drawable.book)
}

val screenItems = listOf(
    Screen.Home,
    Screen.Books
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar {
                screenItems.forEach { screen ->
                    BottomNavigationItem(
                        stringResource(id = screen.nameResourceId),
                        painterResource(id = screen.iconResourceId),
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(screen.route) {
                                    inclusive = true
                                }
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
//            editNavigation(navController)
        }
    }
}


@Preview
@Composable
fun AppNavigationPreview() {
    AppNavigation()
}