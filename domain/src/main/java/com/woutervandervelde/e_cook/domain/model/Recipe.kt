package com.woutervandervelde.e_cook.domain.model

data class Recipe(
    val name: String,
    val description: String?,
    val tags: List<Tag>?,
    val notes: String?,
    val image: String?,
    val source: Source,
) {
    companion object {
        fun Empty(): Recipe = Recipe(
            name = "",
            description = null,
            tags = null,
            notes = null,
            image = null,
            source = Source.Manual
        )
    }
}