package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.model.Resource

interface ShoppingListRepository {

    suspend fun getShoppingList(): Resource<List<ShoppingItemEntity>>

    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity): Long

    suspend fun insertBatchShoppingItems(shoppingItems: List<ShoppingItemEntity>)

    suspend fun toggleShoppingListItem(shoppingItemId: Int, checked: Boolean)

    suspend fun deleteShoppingItem(shoppingItemId: Int)

    suspend fun deleteAllShoppingItems()
}