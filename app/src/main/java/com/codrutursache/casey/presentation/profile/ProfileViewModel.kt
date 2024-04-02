package com.codrutursache.casey.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.domain.usecases.GetProfileDetailsUseCase
import com.codrutursache.casey.domain.usecases.GetSavedRecipesUseCase
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDetailsUseCase: GetProfileDetailsUseCase,
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
) : ViewModel() {
    val displayName get() = getProfileDetailsUseCase().displayName
    val photoUrl get() = getProfileDetailsUseCase().photoUrl
    var savedRecipesIds = mutableStateOf<Response<List<RecipeResponse>>>(Response.Success(null))
        private set

    init {
        getSavedRecipesIds()
    }


    private fun getSavedRecipesIds() {
        viewModelScope.launch {
            savedRecipesIds.value = Response.Loading
            savedRecipesIds.value = getSavedRecipesUseCase()
        }
    }

}
