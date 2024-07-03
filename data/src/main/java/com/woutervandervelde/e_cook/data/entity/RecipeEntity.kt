package com.woutervandervelde.e_cook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.Source
import com.woutervandervelde.e_cook.domain.model.Tag

@Entity(tableName = "recipe")
@TypeConverters(Converters::class)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String?,
    val tags: List<Tag>?,
    val notes: String?,
    val image: String?,
    @ColumnInfo(defaultValue = "0") val source: Source,
) {
    constructor(
        name: String,
        description: String?,
        tags: List<Tag>?,
        notes: String?,
        image: String?,
        source: Source
    ) : this(
        0,
        name,
        description,
        tags,
        notes,
        image,
        source,
    )

    fun toModel() = Recipe(
        id, name, description, tags, notes, image, source
    )

    companion object {
        fun fromModel(item: Recipe) =
            RecipeEntity(
                item.name,
                item.description,
                item.tags,
                item.notes,
                item.image,
                item.source
            )
    }
}