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
    @PrimaryKey(autoGenerate = true) val recipeId: Long,
    val name: String,
    val description: String,
    val tags: List<Tag>,
    val notes: String?,
    val image: String?,
    @ColumnInfo(defaultValue = "0") val source: Source,
    val steps: List<String>
) {
    fun toModel() = Recipe(
        recipeId, name, description, tags, notes, image, source, steps
    )

    companion object {
        fun fromModel(item: Recipe) =
            RecipeEntity(
                item.id,
                item.name,
                item.description,
                item.tags,
                item.notes,
                item.image,
                item.source,
                item.steps
            )
    }
}