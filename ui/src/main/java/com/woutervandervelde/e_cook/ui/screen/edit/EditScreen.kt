package com.woutervandervelde.e_cook.ui.screen.edit

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EditScreen(onClick: () -> Unit) {
    Text(text = "Edit")
    Button(onClick = onClick) {
        Text(text = "switch")
    }
}