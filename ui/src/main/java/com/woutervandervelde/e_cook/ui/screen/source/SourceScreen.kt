package com.woutervandervelde.e_cook.ui.screen.source

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiEvent
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiState

@Composable
fun SourceScreen(
    uiState: SourceUiState,
    uiEvent: (SourceUiEvent) -> Unit
) {
    Column {
        Text(text = "Sources...")
        Text(text = "newInstagramUrl: ${uiState.temp}")
    }
}