package com.codrutursache.casey.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.GetProfileDetailsUseCase
import com.codrutursache.casey.domain.usecases.GetSavedRecipesUseCase
import com.codrutursache.casey.domain.model.Resource
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
    val email get() = getProfileDetailsUseCase().email
    var savedRecipesIds = mutableStateOf<Resource<List<RecipeResponse>>>(Resource.Success(null))
        private set


    fun getSavedRecipesIds() {
        viewModelScope.launch {
            savedRecipesIds.value = Resource.Loading
            savedRecipesIds.value = getSavedRecipesUseCase()
        }
    }

}
