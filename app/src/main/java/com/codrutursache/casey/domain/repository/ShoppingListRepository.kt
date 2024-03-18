package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.domain.model.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    suspend fun getShoppingList(): List<ShoppingItemEntity>

    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity)
}