package com.woutervandervelde.e_cook.domain.model

data class Recipe(
    val id: Int,
    val name: String,
    val description: String?,
    val tags: List<Tag>?,
    val notes: String?,
    val image: String?,
//    val source: Source,
)