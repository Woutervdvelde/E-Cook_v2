package com.woutervandervelde.e_cook.ui.screen.edit.components

import android.util.Log
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Dialog
import com.woutervandervelde.e_cook.domain.ext.capitalizeWords
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.component.Tag
import com.woutervandervelde.e_cook.ui.component.TextInput
import com.woutervandervelde.e_cook.ui.theme.Size12
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size200
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size4
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun IngredientsSelectionModal(
    onDismissRequest: () -> Unit,
    ingredients: List<Ingredient>,
    onIngredientSelected: (name: String, new: Boolean) -> Unit
) {
    var selectIngredientState by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Size16)
                .height(Size360),
            shape = RoundedCornerShape(Size16)
        ) {
            AnimatedVisibility(
                visible = selectIngredientState,
                exit = slideOutHorizontally(targetOffsetX = { -it }),
                modifier = Modifier.fillMaxSize()
            ) {
                IngredientSelection(
                    ingredients = ingredients,
                    onIngredientSelected = { name, new ->
                        selectIngredientState = false
                    })
            }
            AnimatedVisibility(
                visible = !selectIngredientState,
                enter = slideIn(
                    animationSpec = tween(durationMillis = 1000),
                    initialOffset = { IntOffset(it.width * 2, it.height) }),
                modifier = Modifier.fillMaxSize()
            ) {
                QuantitySelection()
            }
        }
    }
}

@Composable
private fun IngredientRow(name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = Size4)
    ) {
        Text(text = name)
    }
}

@Composable
private fun IngredientSelection(
    ingredients: List<Ingredient>,
    onIngredientSelected: (name: String, new: Boolean) -> Unit
) {
    val filteredIngredients = remember { mutableStateOf(ingredients) }
    val currentText = remember { mutableStateOf("") }
    val shouldShowAddIngredient = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(Size16)
    ) {
        TextInput(
            placeholder = stringResource(R.string.edit_section_ingredients_filter_or_add),
            onValueChange = { inputValue ->
                val inputText = inputValue.text.trim().lowercase()
                currentText.value = inputText

                shouldShowAddIngredient.value = inputText.isNotBlank() &&
                        ingredients.none { it.name.lowercase() == inputText }

                filteredIngredients.value = ingredients.filter {
                    it.name.lowercase().contains(inputText)
                }
            })
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (shouldShowAddIngredient.value) {
                IngredientRow(
                    name = "${stringResource(R.string.edit_section_ingredients_add_ingredient)} \"${currentText.value.capitalizeWords()}\"",
                    onClick = {
                        onIngredientSelected(
                            currentText.value.capitalizeWords(),
                            true
                        )
                    })
            }
            filteredIngredients.value.map { ingredient ->
                IngredientRow(
                    name = ingredient.name,
                    onClick = { onIngredientSelected(ingredient.name, false) })
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuantitySelection() {
    var selectedUnit by remember { mutableStateOf(MeasurementUnit.MILLILITER) }

    Column(
        modifier = Modifier.padding(Size16),
        verticalArrangement = Arrangement.spacedBy(Size16)
    ) {
        TextInput(
            placeholder = "0.0",
            onValueChange = {

            }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            FlowRow {
                MeasurementUnit.entries.map {
                    Tag(
                        name = it.text,
                        selected = it == selectedUnit,
                        onSelectChange = { selected -> if (selected) selectedUnit = it },
                        modifier = Modifier.padding(end = Size8, bottom = Size8),
                        toggleable = false
                    )
                }
            }
        }

        IconButton(text = "Add to recipe", icon = painterResource(R.drawable.add), onClick = {
            Log.e(
                "TAG",
                "QuantitySelection: $selectedUnit",

            ) }, modifier = Modifier.weight(.25f))
    }
}