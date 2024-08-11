package com.woutervandervelde.e_cook.domain.ai

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.FunctionType
import com.google.ai.client.generativeai.type.Schema
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.woutervandervelde.e_cook.domain.BuildConfig
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Tag
import org.json.JSONObject

object GenerativeModel {
    private var model: GenerativeModel? = null

    private val nameSchema = Schema(
        name = "name",
        description = "The name of the ingredient",
        type = FunctionType.STRING
    )

    private val quantitySchema = Schema(
        name = "quantity",
        description = "The quantity of the ingredient",
        type = FunctionType.NUMBER
    )

    private val unitSchema = Schema(
        name = "unit",
        description = "The unit of the ingredient",
        type = FunctionType.STRING
    )

    private val ingredientsSchema = Schema(
        name = "ingredients",
        description = "List of ingredients",
        type = FunctionType.ARRAY,
        items = Schema(
            name = "ingredient",
            description = "Ingredient object",
            type = FunctionType.OBJECT,
            properties = mapOf(
                "name" to nameSchema,
                "quantity" to quantitySchema,
                "unit" to unitSchema
            )
        )
    )

    private val tagsSchema = Schema(
        name = "tags",
        description = "List of tags",
        type = FunctionType.ARRAY,
        items = Schema(
            name = "tag",
            description = "Tag",
            type = FunctionType.STRING
        )
    )

    private val stepsSchema = Schema(
        name = "steps",
        description = "List of steps",
        type = FunctionType.ARRAY,
        items = Schema(
            name = "step",
            description = "Step",
            type = FunctionType.STRING
        )
    )

    private val descriptionSchema = Schema(
        name = "description",
        description = "Description of the recipe",
        type = FunctionType.STRING
    )

    fun getModel(): GenerativeModel {
        if (model == null)
            model = GenerativeModel(
                "gemini-1.5-pro-latest",
                BuildConfig.GEMINI_API_KEY,
                generationConfig = generationConfig {
                    responseMimeType = "application/json"
                    responseSchema = Schema(
                        name = "response",
                        description = "Response object",
                        type = FunctionType.OBJECT,
                        properties = mapOf(
                            "name" to nameSchema,
                            "description" to descriptionSchema,
                            "tags" to tagsSchema,
                            "steps" to stepsSchema,
                            "ingredients" to ingredientsSchema
                        )
                    )
                },
                systemInstruction = content { text("Convert the data from the video and the user input into a recipe. For the ingredient measurment unit use one of the following: ${MeasurementUnit::class.java.enumConstants?.map { it.name }}. A tag should describe the catogory of the recipe. For the tag(s) use one or multiple of the following: ${Tag::class.java.enumConstants?.map { it.name }}") }
            )

        return model!!
    }
}