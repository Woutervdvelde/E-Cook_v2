package com.woutervandervelde.e_cook.domain.model

data class Recipe(
    val id: Long,
    val name: String,
    val description: String?,
    val tags: List<Tag>,
    val notes: String?,
    val image: String?,
    val source: Source,
    val steps: List<String>
) {
    companion object {
        fun Empty(): Recipe = Recipe(
            id = 0,
            name = "",
            description = null,
            tags = listOf(),
            notes = null,
            image = null,
            source = Source.Manual,
            steps = listOf()
        )
    }
}