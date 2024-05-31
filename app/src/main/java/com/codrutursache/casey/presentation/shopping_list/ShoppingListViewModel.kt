package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.usecases.ClearShoppingListUseCase
import com.codrutursache.casey.domain.usecases.GetShoppingListUseCase
import com.codrutursache.casey.domain.usecases.ToggleShoppingListItemUseCase
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.usecases.UpdateShoppingListItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val toggleShoppingListItemUseCase: ToggleShoppingListItemUseCase,
    private val clearShoppingListUseCase: ClearShoppingListUseCase,
    private val updateShoppingListItemUseCase: UpdateShoppingListItemUseCase
) : ViewModel() {


    var shoppingList = mutableStateOf<Resource<List<ShoppingItemEntity>>>(
        Resource.Success(
            emptyList()
        )
    )

    init {
        getShoppingList()
    }

    fun getShoppingList() {
        viewModelScope.launch {
            shoppingList.value = Resource.Loading
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
            shoppingList.value = Resource.Loading
            clearShoppingListUseCase()
            shoppingList.value = Resource.Success(emptyList())
        }
    }

    fun updateShoppingListItem(shoppingItem: ShoppingItemEntity) {
        viewModelScope.launch {
            updateShoppingListItemUseCase(shoppingItem)
            when (val currentList = shoppingList.value) {
                is Resource.Success -> {
                    val updatedList = currentList.data?.map {
                        if (it.id == shoppingItem.id) {
                            shoppingItem
                        } else {
                            it
                        }
                    }
                    shoppingList.value = Resource.Success(updatedList!!)
                }

                else -> {
                    // Do nothing
                }
            }
        }
    }
}