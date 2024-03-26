package com.codrutursache.casey.data.repository

import com.codrutursache.casey.data.data_source.ShoppingListDao
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import com.codrutursache.casey.util.Response
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override suspend fun getShoppingList(): Response<List<ShoppingItemEntity>> =
        try {
            val shoppingList = shoppingListDao.getShoppingList()
            Response.Success(shoppingList)
        } catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity) {
        shoppingListDao.insertItem(shoppingItem)
    }

    override suspend fun insertBatchShoppingItems(shoppingItems: List<ShoppingItemEntity>) {
        shoppingListDao.insertBatchItems(shoppingItems)
    }
}