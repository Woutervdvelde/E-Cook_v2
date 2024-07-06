package com.woutervandervelde.e_cook.domain.model

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat.getString
import com.woutervandervelde.e_cook.domain.R

data class Recipe(
    val id: Int,
    val name: String,
    val description: String?,
    val tags: List<Tag>?,
    val notes: String?,
    val image: String?,
    val source: Source,
) {
    companion object {
        fun Empty(): Recipe = Recipe(
            id = 0,
            name = "",
            description = null,
            tags = null,
            notes = null,
            image = null,
            source = Source.Manual
        )
    }
}