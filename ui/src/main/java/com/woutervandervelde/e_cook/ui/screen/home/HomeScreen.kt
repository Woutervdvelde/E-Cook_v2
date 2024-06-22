package com.woutervandervelde.e_cook.ui.screen.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(onClick: () -> Unit) {
    Text(text = "Home")
    Button(onClick = onClick) {
        Text(text = "switch")
    }
}