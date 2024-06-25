package com.woutervandervelde.e_cook.ui.screen.books

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksNavEvent

@Composable
fun BooksScreen(navEvent: (BooksNavEvent) -> Unit) {
    Column {
        Text(text = "Books view...")
    }
}