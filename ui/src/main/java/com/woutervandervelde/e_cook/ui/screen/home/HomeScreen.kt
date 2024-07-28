package com.woutervandervelde.e_cook.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.theme.Size128
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size32
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size56

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(
    uiState: HomeUiState,
    uiEvent: (HomeUiEvent) -> Unit
) {
    Column {
        Header(
            onNewRecipe = { uiEvent(HomeUiEvent.OnAddRecipeClick) },
            onRandomRecipe = { uiEvent(HomeUiEvent.OnRandomRecipeClick) }
        )
    }
}

@Composable
private fun Header(onNewRecipe: () -> Unit, onRandomRecipe: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Size360)
    ) {
        Image(
            painterResource(id = R.drawable.header_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(horizontal = Size16)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(
                        id = R.drawable.ecook_logo
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = Size16)
                        .width(Size128)
                        .height(Size56)
                )
            }
            Text(
                text = stringResource(R.string.home_header_slogan),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = Size32)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Size16)
            ) {
                IconButton(
                    text = stringResource(R.string.button_add_recipe),
                    icon = painterResource(id = R.drawable.add),
                    onClick = onNewRecipe,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(Size16))
                IconButton(
                    text = stringResource(R.string.button_random_recipe),
                    icon = painterResource(id = R.drawable.dice_5),
                    onClick = onRandomRecipe,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}