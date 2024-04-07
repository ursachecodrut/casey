package com.codrutursache.casey.presentation.base.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.usecases.ClearShoppingListUseCase
import com.codrutursache.casey.domain.usecases.SaveRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val clearShoppingListUseCase: ClearShoppingListUseCase
) : ViewModel() {



}