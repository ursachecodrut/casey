package com.codrutursache.casey.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codrutursache.casey.domain.model.ShoppingItemEntity

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM ShoppingItemEntity")
    suspend fun getShoppingList(): List<ShoppingItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchItems(items: List<ShoppingItemEntity>)
}
