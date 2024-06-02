package com.codrutursache.casey.presentation.recipes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.GetRecipesUseCase
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.util.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 10
    }

    private var offset = 0
    private var recipeName = ""

    var recipeListDto = mutableStateOf<List<RecipeResponse>>(emptyList())
        private set

    private var isLoading by mutableStateOf(false)

    init {
        getRecipes()
    }

    fun searchRecipes(recipeName: String) {
        deBounceSearch(recipeName)
    }

    fun getRecipes() {
        fetchRecipes(this.recipeName)
    }

    private fun fetchRecipes(
        recipeName: String
    ) {
        viewModelScope.launch {
            isLoading = true
            when (val result =
                getRecipesUseCase(number = PAGE_SIZE, offset = offset, recipeName = recipeName)) {
                is Resource.Success -> {
                    recipeListDto.value += result.data!!.results
                    offset += PAGE_SIZE
                }

                is Resource.Failure -> {
                    Log.e("RecipesListViewModel", "Error: ${result.e}")
                }

                else -> {}
            }
            isLoading = false
        }
    }

    private val deBounceSearch = debounce<String>(
        coroutineScope = viewModelScope,
        waitMs = 700L
    ) { recipeName ->
        this.recipeName = recipeName
        offset = 0
        recipeListDto.value = emptyList()
        fetchRecipes(recipeName)
    }
}