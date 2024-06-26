package com.woutervandervelde.e_cook.ui.screen.source

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent

@Composable
fun SourceScreen(navEvent: (SourceNavEvent) -> Unit) {
    Column {
        Text(text = "Sources...")
    }
}