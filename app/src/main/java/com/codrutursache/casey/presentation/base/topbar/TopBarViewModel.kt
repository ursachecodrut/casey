package com.codrutursache.casey.presentation.base.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.SaveRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val saveRecipeUseCase: SaveRecipeUseCase,
) : ViewModel() {


    fun saveRecipe(recipeShort: RecipeResponse) = viewModelScope.launch {
        saveRecipeUseCase(recipeShort)
    }
}