package com.woutervandervelde.e_cook.ui.screen.books

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent

@Composable
fun BooksScreen(navEvent: (EditNavEvent) -> Unit) {
    Column {
        Text(text = "Books view...")
    }
}