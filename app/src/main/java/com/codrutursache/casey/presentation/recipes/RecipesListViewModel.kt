package com.codrutursache.casey.presentation.recipes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.remote.model.RecipeListResponse
import com.codrutursache.casey.data.remote.model.Response
import com.codrutursache.casey.domain.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    var recipes by mutableStateOf<Response<RecipeListResponse>>(Response.Loading)


    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            recipes = Response.Loading
            recipes = recipesRepository.getRecipes()
        }
    }

}