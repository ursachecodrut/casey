package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.util.Response

interface ShoppingListRepository {

    suspend fun getShoppingList(): Response<List<ShoppingItemEntity>>

    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity)

    suspend fun insertBatchShoppingItems(shoppingItems: List<ShoppingItemEntity>)
}