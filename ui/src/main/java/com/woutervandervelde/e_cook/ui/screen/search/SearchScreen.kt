package com.woutervandervelde.e_cook.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.ui.screen.search.navigation.SearchNavEvent

@Composable
fun SearchScreen(navEvent: (SearchNavEvent) -> Unit) {
    Column {
        Text(text = "Search screen...")
    }
}