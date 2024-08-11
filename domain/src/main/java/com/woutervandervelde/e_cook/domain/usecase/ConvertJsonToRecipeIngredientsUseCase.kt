package com.woutervandervelde.e_cook.domain.usecase

import android.util.Log
import com.woutervandervelde.e_cook.domain.ext.capitalizeWords
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.Source
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ConvertJsonToRecipeIngredientsUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository
) {
    @Serializable
    private data class IngredientJson(val name: String, val quantity: Double, val unit: String)

    @Serializable
    private data class RecipeJson(
        val name: String,
        val description: String,
        val tags: List<String>,
        val steps: List<String>,
        val ingredients: List<IngredientJson>
    )

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun invoke(json: String, videoInfo: InstagramVideoInfo): Long = withContext(Dispatchers.IO) {
        try {
            val recipeJson = Json.decodeFromString<RecipeJson>(json)
            val recipe = Recipe(
                id = 0,
                name = recipeJson.name,
                description = recipeJson.description,
                tags = recipeJson.tags.map { Tag.valueOf(it) },
                image = videoInfo.thumbnail,
                source = Source.Instagram,
                steps = recipeJson.steps
            )
            recipe.id = recipeRepository.insertRecipe(recipe)

            val recipeIngredients: List<RecipeIngredient> = recipeJson.ingredients.map {
                val ingredient = Ingredient(it.name.capitalizeWords())
                ingredientRepository.insertIngredient(ingredient)
                RecipeIngredient(
                    ingredient, MeasurementUnit.valueOf(it.unit), it.quantity
                )
            }
            recipeRepository.insertRecipeIngredients(recipe, recipeIngredients)
            return@withContext recipe.id
        } catch (e: MissingFieldException) {
            //Gemini response doesn't contain all required fields
            return@withContext -1L
        } catch (e: Exception) {
            //General problem, oh oh
            return@withContext -1L
        }
    }
}