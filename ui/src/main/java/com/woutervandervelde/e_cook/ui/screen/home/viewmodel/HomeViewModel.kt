package com.woutervandervelde.e_cook.ui.screen.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    init {
        Log.e("TAG", "init")
    }
}