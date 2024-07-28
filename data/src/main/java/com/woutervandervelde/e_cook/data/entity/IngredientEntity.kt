package com.woutervandervelde.e_cook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.woutervandervelde.e_cook.domain.model.Ingredient

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey val name: String
) {
    fun toModel() = Ingredient(name)

    companion object {
        fun fromModel(ingredient: Ingredient) =
            IngredientEntity(ingredient.name)
    }
}
