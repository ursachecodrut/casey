package com.codrutursache.casey.presentation.recipes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.GetRecipesUseCase
import com.codrutursache.casey.util.Response
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
                is Response.Success -> {
                    recipeListDto.value += result.data!!.results
                    offset += pageSize
                    isLoading.value = false
                }

                is Response.Failure -> {
                    isLoading.value = false
                }

                is Response.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
}