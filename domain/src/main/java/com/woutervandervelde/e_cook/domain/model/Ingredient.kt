package com.woutervandervelde.e_cook.domain.model

data class Ingredient(
    val name: String
) {
    companion object {
        fun Emtpy() = Ingredient("")
    }
}
