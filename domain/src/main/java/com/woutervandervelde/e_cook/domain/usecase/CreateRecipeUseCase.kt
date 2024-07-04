package com.woutervandervelde.e_cook.domain.usecase

import android.util.Log
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import javax.inject.Inject

class CreateRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    fun invoke() {
        Log.e("TAG", "execute: ", )
    }
}