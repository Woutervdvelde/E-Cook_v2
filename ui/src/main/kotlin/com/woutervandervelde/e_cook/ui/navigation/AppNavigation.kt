package com.woutervandervelde.e_cook.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.woutervandervelde.e_cook.ui.feature.home.homeNavigation

@Composable
internal fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        homeNavigation(navController)
    }
}

@Composable
fun Test() {
    Text("Test")
}