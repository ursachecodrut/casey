package com.codrutursache.casey.presentation.recipes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.remote.dto.RecipeListDto
import com.codrutursache.casey.domain.usecases.GetRecipesUseCase
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    var recipes by mutableStateOf<Response<RecipeListDto>>(Response.Success(null))
        private set

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            recipes = Response.Loading
            recipes = getRecipesUseCase()
        }
    }

}