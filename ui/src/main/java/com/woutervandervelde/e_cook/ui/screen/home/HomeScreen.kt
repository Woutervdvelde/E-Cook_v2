package com.woutervandervelde.e_cook.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.provider.FontsContractCompat.Columns
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeNavEvent

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(navEvent: (HomeNavEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        Text(text = "Home")
        Button(onClick = { navEvent(HomeNavEvent.ToEdit) }) {
            Text(text = "switch")
        }
    }
}