package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.usecases.ClearShoppingListUseCase
import com.codrutursache.casey.domain.usecases.GetShoppingListUseCase
import com.codrutursache.casey.domain.usecases.ToggleShoppingListItemUseCase
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val toggleShoppingListItemUseCase: ToggleShoppingListItemUseCase,
    private val clearShoppingListUseCase: ClearShoppingListUseCase,
) : ViewModel() {


    var shoppingList = mutableStateOf<Response<List<ShoppingItemEntity>>>(
        Response.Success(
            emptyList()
        )
    )

    init {
        getShoppingList()
    }

    fun getShoppingList() {
        viewModelScope.launch {
            shoppingList.value = Response.Loading
            shoppingList.value = getShoppingListUseCase()
        }
    }

    fun toggleShoppingListItem(shoppingItemId: Int, checked: Boolean) {
        viewModelScope.launch {
            toggleShoppingListItemUseCase(shoppingItemId, checked)
        }
    }

    fun clearShoppingList() {
        viewModelScope.launch {
            shoppingList.value = Response.Loading
            clearShoppingListUseCase()
            shoppingList.value = Response.Success(emptyList())
        }
    }
}