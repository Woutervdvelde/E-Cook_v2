package com.woutervandervelde.e_cook.ui.screen.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.component.IngredientItem
import com.woutervandervelde.e_cook.ui.component.Tag
import com.woutervandervelde.e_cook.ui.component.Input
import com.woutervandervelde.e_cook.ui.screen.edit.components.IngredientsSelectionModal
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiEvent
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiState
import com.woutervandervelde.e_cook.ui.theme.Size0
import com.woutervandervelde.e_cook.ui.theme.Size12
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size20
import com.woutervandervelde.e_cook.ui.theme.Size8
import androidx.compose.material3.IconButton as DefaultIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditScreen(
    uiState: EditUiState,
    uiEvent: (EditUiEvent) -> Unit
) {
    val recipe: Recipe = uiState.recipe

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.edit_header_title),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                navigationIcon = {
                    DefaultIconButton(onClick = { uiEvent(EditUiEvent.OnBack) }) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = stringResource(
                                R.string.button_back
                            )
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(Size16),
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    end = Size16,
                    bottom = Size0,
                    start = Size16
                )
                .verticalScroll(rememberScrollState())
        ) {
            ImageSection(modifier = Modifier.padding(top = Size16))
            NameSection()
            DescriptionSection()
            TagsSection()
            IngredientsSection(uiState.allIngredients)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun ImageSection(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Size8)
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1519915028121-7d3463d20b13?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(Size20))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Size8),
            modifier = modifier.weight(1f)
        ) {
            SectionTitle(title = stringResource(R.string.edit_section_image_title))
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

@Composable
fun NameSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_name_title))
        Input(
            placeholder = stringResource(R.string.edit_section_name_placeholder),
            singleLine = true,
            onValueChange = {}
        )
    }
}

@Composable
fun DescriptionSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_description_title))
        Input(
            placeholder = stringResource(R.string.edit_section_description_placeholder),
            minLines = 2,
            onValueChange = {}
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection() {
    var selectedTags: MutableList<Tag> = mutableListOf()

    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_tags_title))
        FlowRow {
            Tag.entries.map {
                Tag(
                    name = it.name,
                    onSelectChange = { selected ->
                        if (selected) selectedTags.add(it)
                        else selectedTags.remove(it)
                    },
                    modifier = Modifier.padding(end = Size12, bottom = Size12)
                )
            }
        }
    }
}

@Composable
fun IngredientsSection(ingredients: List<Ingredient>) {
    var showIngredientModal by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_ingredients_title))
        Column {
            IngredientItem()
            IconButton(
                text = stringResource(R.string.edit_section_ingredients_button_add),
                icon = painterResource(R.drawable.add),
                onClick = { showIngredientModal = true })
        }
    }

    if (showIngredientModal) {
        IngredientsSelectionModal(
            onDismissRequest = {
                showIngredientModal = false
            },
            onIngredientSelected = { ingredient, new, unit, quantity ->
                showIngredientModal = false
            },
            ingredients = ingredients
        )
    }
}


