package com.codrutursache.casey.data.repository

import com.codrutursache.casey.data.data_source.ShoppingListDao
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override suspend fun getShoppingList(): List<ShoppingItemEntity> {
        return shoppingListDao.getShoppingList()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity) {
        shoppingListDao.insertShoppingItem(shoppingItem)
    }
}