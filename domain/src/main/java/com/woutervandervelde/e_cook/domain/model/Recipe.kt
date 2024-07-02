package com.woutervandervelde.e_cook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val name: String,
    val description: String?,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    constructor(name: String, description: String?) : this(name, description, 0)
}