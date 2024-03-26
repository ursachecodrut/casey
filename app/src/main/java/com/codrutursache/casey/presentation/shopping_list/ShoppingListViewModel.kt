package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.usecases.GetShoppingListUseCase
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase
) : ViewModel() {


    var shoppingList = mutableStateOf<Response<List<ShoppingItemEntity>>>(
        Response.Success(
            emptyList()
        )
    )

    init {
        getShoppingList()
    }

    private fun getShoppingList() {
        viewModelScope.launch {
            shoppingList.value = Response.Loading
            shoppingList.value = getShoppingListUseCase()
        }
    }
}