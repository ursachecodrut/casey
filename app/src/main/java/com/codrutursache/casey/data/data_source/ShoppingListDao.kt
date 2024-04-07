package com.codrutursache.casey.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codrutursache.casey.domain.model.ShoppingItemEntity

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM ShoppingItemEntity")
    suspend fun getAllItems(): List<ShoppingItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchItems(items: List<ShoppingItemEntity>)

    @Query("UPDATE ShoppingItemEntity SET checked = :checked WHERE id = :id")
    suspend fun toggleItem(id: Int, checked: Boolean)

    @Query("DELETE FROM ShoppingItemEntity WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("DELETE FROM ShoppingItemEntity")
    suspend fun deleteAllItems()
}
