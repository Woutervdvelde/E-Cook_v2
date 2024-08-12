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
                systemInstruction = content { text("Convert the data from the video and the user input into a complete and detailed recipe. Ensure that the recipe includes: 1. ingredients list: Provide a comprehensive list of all ingredients required for the recipe. Include accurate measurements for each ingredient using one of the following units: ${MeasurementUnit::class.java.enumConstants?.map { it.name }}. Make sure that the measurements are appropriate and correspond to the quantities used in the recipe steps. 2. Recipe steps: Provide clear, step-by-step instructions for preparing the recipe. Each step should be specific and detailed. For example, instead of saying 'Make the bagel dough,' provide detailed instructions on how to make the bagel dough, including mixing, kneading, and any other necessary processes. Ensure that each step is actionable and easy to follow. 3. tags: Include one or more tags that describe the category of the recipe. Use the following tags: ${Tag::class.java.enumConstants?.map { it.name }}. Choose tags that best represent the recipe’s type and characteristics. If any data is not provided in text form or in the provided pictures, select the most suitable value or estimate based on standard recipes. The recipe should be as complete and informative as possible.") }
            )

        return model!!
    }
}