package com.woutervandervelde.e_cook.ui.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.presentation.feature.home.HomeNavEvent
import com.woutervandervelde.e_cook.presentation.feature.home.HomeUiEvent
import com.woutervandervelde.e_cook.presentation.feature.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    uiEvent: (HomeUiEvent) -> Unit
) {
    Text("Home")
}