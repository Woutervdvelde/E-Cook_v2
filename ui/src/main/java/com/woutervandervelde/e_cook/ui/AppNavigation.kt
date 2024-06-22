package com.woutervandervelde.e_cook.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.woutervandervelde.e_cook.ui.screen.edit.EditScreen
import com.woutervandervelde.e_cook.ui.screen.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Edit

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Home) {
        composable<Home> { HomeScreen(onClick = { navController.navigate(Edit) }) }
        composable<Edit> { EditScreen(onClick = { navController.navigate(Home) }) }
    }
}