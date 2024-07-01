package com.woutervandervelde.e_cook.ui.screen.books

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.woutervandervelde.e_cook.ui.screen.books.navigation.BooksNavEvent
import kotlin.math.absoluteValue

@Composable
fun BooksScreen(navEvent: (BooksNavEvent) -> Unit) {
    Column {
        Text(text = "Books view...")
    }
}