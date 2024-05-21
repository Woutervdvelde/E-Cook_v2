package com.woutervandervelde.e_cook.ui.navigation

import kotlinx.serialization.Serializable

internal sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Search(val filters: String?) : Screen
}