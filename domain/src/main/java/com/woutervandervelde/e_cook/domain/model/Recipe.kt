package com.woutervandervelde.e_cook.domain.model

data class Recipe(
    var id: Long,
    val name: String,
    val description: String,
    val tags: List<Tag>,
    val notes: String? = null,
    val image: String? = null,
    val source: Source,
    val steps: List<String>
) {
    companion object {
        fun Empty(): Recipe = Recipe(
            id = 0,
            name = "",
            description = "",
            tags = listOf(),
            notes = null,
            image = null,
            source = Source.Manual,
            steps = listOf()
        )
    }
}