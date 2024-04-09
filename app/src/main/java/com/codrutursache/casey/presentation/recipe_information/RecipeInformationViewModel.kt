package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.AddIngredientsToShoppingListUseCase
import com.codrutursache.casey.domain.usecases.GetRecipeInformationUseCase
import com.codrutursache.casey.domain.usecases.SaveRecipeUseCase
import com.codrutursache.casey.domain.usecases.UnsaveRecipeUseCase
import com.codrutursache.casey.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInformationViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val addIngredientsToShoppingListUseCase: AddIngredientsToShoppingListUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val unsaveRecipeUseCase: UnsaveRecipeUseCase,
) : ViewModel() {

    var recipeInformation = mutableStateOf<Resource<RecipeInformationResponse>>(
        Resource.Success(null)
    )
        private set

    fun getRecipeInformation(id: Int) {
        viewModelScope.launch {
            recipeInformation.value = Resource.Loading
            recipeInformation.value = getRecipeInformationUseCase(id)
        }
    }

    fun addIngredientsToShoppingList(
        ingredients: List<ExtendedIngredientResponse>,
        numberOfServings: Int
    ) {
        viewModelScope.launch {
            addIngredientsToShoppingListUseCase(ingredients, numberOfServings)
        }
    }

    fun saveRecipe(recipeShort: RecipeResponse) = viewModelScope.launch {
        saveRecipeUseCase(recipeShort)
    }

    fun unsaveRecipe(recipeId: Int) = viewModelScope.launch {
        unsaveRecipeUseCase(recipeId)
    }
}