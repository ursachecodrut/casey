package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.domain.usecases.GetRecipeInformationUseCase
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInformationViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
) : ViewModel() {

    var recipeInformation = mutableStateOf<Response<RecipeInformationResponse>>(
        Response.Success(null)
    )
        private set

    suspend fun getRecipeInformation(id: Int) {
        viewModelScope.launch {
            recipeInformation.value = Response.Loading
            recipeInformation.value = getRecipeInformationUseCase(id)
        }
    }
}