package com.codrutursache.casey.data.repository

import android.util.Log
import com.codrutursache.casey.data.data_source.ShoppingListDao
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import com.codrutursache.casey.domain.model.Resource
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override suspend fun getShoppingList(): Resource<List<ShoppingItemEntity>> =
        try {
            val shoppingList = shoppingListDao.getAllItems()
            Resource.Success(shoppingList)
        } catch (e: Exception) {
            Log.e("ShoppingListRepositoryImpl", "getShoppingList: $e")
            Resource.Failure(e)
        }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity) {
        shoppingListDao.insertItem(shoppingItem)
    }

    override suspend fun insertBatchShoppingItems(shoppingItems: List<ShoppingItemEntity>) {
        val convertedToBaseUnitShoppingItems = shoppingItems.map { it.changeToBaseUnit() }

        val presentShoppingItems = shoppingListDao.getAllItems()

        val updatedShoppingItems =
            mergeShoppingItems(presentShoppingItems, convertedToBaseUnitShoppingItems)

        shoppingListDao.insertBatchItems(updatedShoppingItems)
    }

    override suspend fun toggleShoppingListItem(shoppingItemId: Int, checked: Boolean) {
        shoppingListDao.toggleItem(shoppingItemId, checked)
    }

    override suspend fun deleteShoppingItem(shoppingItemId: Int) {
        shoppingListDao.deleteItem(shoppingItemId)
    }

    override suspend fun deleteAllShoppingItems() {
        shoppingListDao.deleteAllItems()
    }

    private fun mergeShoppingItems(
        presentShoppingItems: List<ShoppingItemEntity>,
        newShoppingItems: List<ShoppingItemEntity>
    ): List<ShoppingItemEntity> {
        return newShoppingItems.map { shoppingItem ->
            val presentItem = presentShoppingItems.find { it.id == shoppingItem.id }
            if (presentItem != null) {
                val updatedQuantity = presentItem.quantity + shoppingItem.quantity
                presentItem.copy(quantity = updatedQuantity)
            } else {
                shoppingItem
            }
        }
    }
}