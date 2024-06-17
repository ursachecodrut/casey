package com.codrutursache.casey.data.repository

import android.util.Log
import com.codrutursache.casey.data.data_source.ShoppingListDao
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.ProfileRepository
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao,
    profileRepository: ProfileRepository,
) : ShoppingListRepository {
    private val userId = profileRepository.userId ?: ""

    override suspend fun getShoppingList(): Resource<List<ShoppingItemEntity>> =
        try {
            val shoppingList = shoppingListDao.getAllItems(userId)
            Resource.Success(shoppingList)
        } catch (e: Exception) {
            Resource.Failure(e)
        }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity): Long {
        val shoppingItemWithUserId = shoppingItem.copy(userId = userId)
        return shoppingListDao.insertItem(shoppingItemWithUserId)
    }


    override suspend fun insertBatchShoppingItems(shoppingItems: List<ShoppingItemEntity>) {
        val convertedShoppingItems = shoppingItems
            .map { it.copy(userId = userId) }
            .map { it.changeToBaseUnit() }
        val presentShoppingItems = shoppingListDao.getAllItems(userId)
        val mergedShoppingItems = mergeShoppingItems(presentShoppingItems, convertedShoppingItems)
        shoppingListDao.insertBatchItems(mergedShoppingItems)
    }

    override suspend fun toggleShoppingListItem(shoppingItemId: Int, checked: Boolean) {
        shoppingListDao.toggleItem(shoppingItemId, checked, userId)
    }

    override suspend fun deleteShoppingItem(shoppingItemId: Int) {
        shoppingListDao.deleteItem(shoppingItemId, userId)
    }

    override suspend fun deleteAllShoppingItems() {
        shoppingListDao.deleteAllItems(userId)
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