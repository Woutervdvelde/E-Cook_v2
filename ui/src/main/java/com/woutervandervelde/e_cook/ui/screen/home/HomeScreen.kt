package com.woutervandervelde.e_cook.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeNavEvent
import com.woutervandervelde.e_cook.ui.screen.home.viewmodel.HomeViewModel

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(
    navEvent: (HomeNavEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(R.string.home_header_slogan), style = MaterialTheme.typography.titleLarge)
        Button(onClick = { navEvent(HomeNavEvent.ToBooks) }) {
            Text(text = "switch")
        }
    }
}