package com.woutervandervelde.e_cook.ui.screen.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiEvent
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiState
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditScreen(
    uiState: EditUiState,
    uiEvent: (EditUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { _ ->
        Column(
            modifier = Modifier.safeDrawingPadding()
        ) {
            Text(text = "Edit")
        }
    }
}