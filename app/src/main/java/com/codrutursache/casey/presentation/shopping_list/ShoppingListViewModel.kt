package com.codrutursache.casey.presentation.shopping_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.usecases.ClearShoppingListUseCase
import com.codrutursache.casey.domain.usecases.GetShoppingListUseCase
import com.codrutursache.casey.domain.usecases.ToggleShoppingListItemUseCase
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.usecases.AddItemToShoppingListUseCase
import com.codrutursache.casey.domain.usecases.DeleteShoppingListItemUseCase
import com.codrutursache.casey.domain.usecases.UpdateShoppingListItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val toggleShoppingListItemUseCase: ToggleShoppingListItemUseCase,
    private val clearShoppingListUseCase: ClearShoppingListUseCase,
    private val updateShoppingListItemUseCase: UpdateShoppingListItemUseCase,
    private val deleteShoppingListItemUseCase: DeleteShoppingListItemUseCase,
    private val addItemToShoppingListUseCase: AddItemToShoppingListUseCase
) : ViewModel() {


    var shoppingList = mutableStateOf<Resource<List<ShoppingItemEntity>>>(
        Resource.Success(
            emptyList()
        )
    )

    init {
        getShoppingList()
    }

    private fun getShoppingList() {
        viewModelScope.launch {
            shoppingList.value = Resource.Loading
            shoppingList.value = getShoppingListUseCase()
        }
    }

    fun addItemToShoppingList(item: ShoppingItemEntity) {
        viewModelScope.launch {
            addItemToShoppingListUseCase(item)
            getShoppingList()
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

    fun deleteShoppingListItem(shoppingItemId: Int) {
        viewModelScope.launch {
            deleteShoppingListItemUseCase(shoppingItemId)
            when (val currentList = shoppingList.value) {
                is Resource.Success -> {
                    val updatedList = currentList.data?.filter { it.id != shoppingItemId }
                    shoppingList.value = Resource.Success(updatedList!!)
                }

                else -> {
                    // Do nothing
                }
            }
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