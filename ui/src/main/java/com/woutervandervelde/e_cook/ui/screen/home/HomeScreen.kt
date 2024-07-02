package com.woutervandervelde.e_cook.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    viewModel: HomeViewModel,
    navEvent: (HomeNavEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(R.string.home_header_slogan), style = MaterialTheme.typography.titleLarge)
        Button(onClick = { navEvent(HomeNavEvent.ToBooks) }) {
            Text(text = "switch")
        }
        
        Button(onClick = {
            viewModel.saveRecipe("test", "test description")
        }) {
            Text(text = "save")
        }
        
        Button(onClick = { viewModel.loadRecipes() }) {
            Text(text = "load")
        }

        Row {
            viewModel.recipes.value.forEach { recipe ->
                Column {
                    Text(text = recipe.id.toString())
                    Text(text = recipe.name)
                    Text(text = recipe.description ?: "no description")
                }
            }
        }
    }
}