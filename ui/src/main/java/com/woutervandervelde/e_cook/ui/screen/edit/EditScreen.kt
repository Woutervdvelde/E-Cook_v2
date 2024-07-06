package com.woutervandervelde.e_cook.ui.screen.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiEvent
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiState
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.theme.Size10
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size20
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditScreen(
    uiState: EditUiState,
    uiEvent: (EditUiEvent) -> Unit
) {
    val recipe: Recipe = uiState.recipe

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { _ ->
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(horizontal = Size16)
        ) {
            ImageSection()
        }
    }
}

@Composable
fun ImageSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Size8)
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1519915028121-7d3463d20b13?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(Size20))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Size8),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.edit_section_image_title),
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(
                text = stringResource(R.string.edit_button_upload),
                icon = painterResource(id = R.drawable.publish),
                onClick = { /*TODO*/ })
            IconButton(
                text = stringResource(R.string.edit_button_url),
                icon = painterResource(id = R.drawable.link),
                onClick = { /*TODO*/ })
        }
    }
}