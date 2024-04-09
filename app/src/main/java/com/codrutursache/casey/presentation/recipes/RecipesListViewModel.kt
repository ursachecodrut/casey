package com.codrutursache.casey.presentation.recipes

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.GetRecipesUseCase
import com.codrutursache.casey.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private var offset = 0
    private val pageSize = 10

    var recipeListDto = mutableStateOf<List<RecipeResponse>>(emptyList())
        private set

    private var isLoading = mutableStateOf(false)

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            when (val result = getRecipesUseCase(number = pageSize, offset = offset)) {
                is Resource.Success -> {
                    recipeListDto.value += result.data!!.results
                    offset += pageSize
                    isLoading.value = false

                    Log.d("RecipesListViewModel", "getRecipes: ${recipeListDto.value}")
                }

                is Resource.Failure -> {
                    isLoading.value = false

                    Log.e("RecipesListViewModel", "getRecipes: ${result.e}")
                }

                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
}