package com.woutervandervelde.e_cook.ui.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.woutervandervelde.e_cook.presentation.feature.home.HomeNavEvent
import com.woutervandervelde.e_cook.presentation.feature.home.HomeViewModel
import com.woutervandervelde.e_cook.ui.ext.RetrieveAsEffect

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    navEvent: (HomeNavEvent) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.navState.RetrieveAsEffect(collector = navEvent)

    HomeScreen(
        uiState = uiState,
        uiEvent = viewModel::onUiEvent
    )
}