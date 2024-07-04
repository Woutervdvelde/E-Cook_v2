package com.woutervandervelde.e_cook.ui.screen.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditScreen(navEvent: (EditNavEvent) -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) { _ ->
        Column {
            Text(text = "Edit")
            Button(onClick = { navEvent(EditNavEvent.Back) }) {
                Text(text = "Back")
            }
        }
    }
}