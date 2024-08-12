package com.woutervandervelde.e_cook.ui.screen.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.ButtonType
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.component.IngredientItem
import com.woutervandervelde.e_cook.ui.component.Input
import com.woutervandervelde.e_cook.ui.component.PagerIndicator
import com.woutervandervelde.e_cook.ui.component.RecipeStepCard
import com.woutervandervelde.e_cook.ui.component.StepsPager
import com.woutervandervelde.e_cook.ui.component.Tag
import com.woutervandervelde.e_cook.ui.screen.edit.components.IngredientsSelectionModal
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiEvent
import com.woutervandervelde.e_cook.ui.screen.edit.presentation.EditUiState
import com.woutervandervelde.e_cook.ui.theme.Size0
import com.woutervandervelde.e_cook.ui.theme.Size12
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size20
import com.woutervandervelde.e_cook.ui.theme.Size200
import com.woutervandervelde.e_cook.ui.theme.Size4
import com.woutervandervelde.e_cook.ui.theme.Size72
import com.woutervandervelde.e_cook.ui.theme.Size8
import androidx.compose.material3.IconButton as DefaultIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    uiState: EditUiState,
    uiEvent: (EditUiEvent) -> Unit
) {
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
        },
        bottomBar = {
            EditControls(
                onSave = { uiEvent(EditUiEvent.OnSaveRecipe) },
                onDiscard = { uiEvent(EditUiEvent.OnDiscardRecipe) }
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
            ImageSection(
                image = uiState.recipeImage,
                modifier = Modifier.padding(top = Size16)
            )
            NameSection(
                name = uiState.recipeName,
                onUpdateName = { name ->
                    uiEvent(EditUiEvent.OnUpdateRecipeName(name))
                }
            )
            DescriptionSection(
                description = uiState.recipeDescription,
                onUpdateDescription = { description ->
                    uiEvent(EditUiEvent.OnUpdateRecipeDescription(description))
                }
            )
            TagsSection(
                tags = uiState.recipeTags,
                onUpdateTags = { tag, selected ->
                    uiEvent(EditUiEvent.OnUpdateRecipeTags(tag, selected))
                }
            )
            IngredientsSection(
                ingredients = uiState.recipeIngredients,
                allIngredients = uiState.allIngredients,
                onNewIngredient = { ingredient, new, unit, quantity ->
                    if (new) uiEvent(EditUiEvent.OnCreateIngredient(ingredient))
                    val recipeIngredient = RecipeIngredient(ingredient, unit, quantity)
                    uiEvent(EditUiEvent.OnAddIngredientToRecipe(recipeIngredient))
                }
            )
            StepsSection(
                steps = uiState.recipeSteps,
                onAddStep = { step ->
                    uiEvent(EditUiEvent.OnAddStepToRecipe(step))
                },
                onDeleteStep = { index ->
                    uiEvent(EditUiEvent.OnDeleteStepFromRecipe(index))
                }
            )
            DeleteSection(
                onDelete = { uiEvent(EditUiEvent.OnDeleteRecipe) }
            )
            Spacer(modifier = Modifier.height(Size72))
        }
    }
}

@Composable
fun EditControls(onSave: () -> Unit, onDiscard: () -> Unit) {
    Row(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0f, 0f, 0f, 0f),
                    MaterialTheme.colorScheme.background
                )
            )
        )
    ) {
        Row(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom))
                .padding(top = Size16, start = Size16, end = Size16)
        ) {
            IconButton(
                text = stringResource(R.string.button_save),
                icon = painterResource(R.drawable.save),
                onClick = onSave,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(Size8))
            IconButton(
                text = stringResource(R.string.button_discard),
                icon = painterResource(R.drawable.close),
                onClick = onDiscard,
                modifier = Modifier.weight(1f),
                buttonType = ButtonType.SECONDARY
            )
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
fun ImageSection(modifier: Modifier = Modifier, image: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Size8)
    ) {
        AsyncImage(
            model = image,
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
fun NameSection(name: String, onUpdateName: (name: String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_name_title))
        Input(
            value = name,
            placeholder = stringResource(R.string.edit_section_name_placeholder),
            singleLine = true,
            onValueChange = { onUpdateName(it) }
        )
    }
}

@Composable
fun DescriptionSection(description: String, onUpdateDescription: (description: String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_description_title))
        Input(
            value = description,
            placeholder = stringResource(R.string.edit_section_description_placeholder),
            minLines = 2,
            onValueChange = { onUpdateDescription(it) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(tags: List<Tag>, onUpdateTags: (tag: Tag, selected: Boolean) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_tags_title))
        FlowRow {
            Tag.entries.map {
                Tag(
                    name = it.name,
                    selected = tags.contains(it),
                    onSelectChange = { selected ->
                        onUpdateTags(it, selected)
                    },
                    modifier = Modifier.padding(end = Size12, bottom = Size12)
                )
            }
        }
    }
}

@Composable
fun IngredientsSection(
    ingredients: List<RecipeIngredient>,
    allIngredients: List<Ingredient>,
    onNewIngredient: (ingredient: Ingredient, new: Boolean, unit: MeasurementUnit, quantity: Double) -> Unit
) {
    var showIngredientModal by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Size8)
    ) {
        SectionTitle(title = stringResource(R.string.edit_section_ingredients_title))
        Column {
            ingredients.map { IngredientItem(it) }
            Spacer(modifier = Modifier.height(Size4))
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
                onNewIngredient(ingredient, new, unit, quantity)
            },
            ingredients = allIngredients
        )
    }
}

@Composable
fun StepsSection(
    steps: List<String>,
    onAddStep: (step: String) -> Unit,
    onDeleteStep: (index: Int) -> Unit
) {
    Column {
        SectionTitle(title = stringResource(R.string.edit_section_steps_title))
        Spacer(modifier = Modifier.height(Size8))

        var showStepModal by remember { mutableStateOf(false) }
        StepsPager(
            steps = steps,
            editable = true,
            onDeleteStep = onDeleteStep,
            onAddStep = { showStepModal = true }
        )

        if (showStepModal) {
            Dialog(onDismissRequest = { showStepModal = false }) {
                val step = remember { mutableStateOf("") }

                Card {
                    Input(
                        value = step.value,
                        onValueChange = { step.value = it },
                        minLines = 3,
                        placeholder = stringResource(R.string.edit_section_steps_add_placeholder)
                    )
                    Spacer(modifier = Modifier.height(Size16))
                    IconButton(
                        text = stringResource(R.string.edit_section_steps_add),
                        icon = painterResource(R.drawable.add),
                        onClick = {
                            onAddStep(step.value)
                            showStepModal = false
                        })
                }
            }
        }
    }
}

@Composable
fun DeleteSection(onDelete: () -> Unit) {
    IconButton(
        text = stringResource(R.string.edit_section_delete_button),
        icon = painterResource(R.drawable.delete),
        onClick = onDelete
    )
}
