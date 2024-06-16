package com.codrutursache.casey.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codrutursache.casey.domain.model.ShoppingItemEntity
import com.google.firebase.auth.FirebaseAuth

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM ShoppingItemEntity WHERE userId = :userId")
    suspend fun getAllItems(userId: String): List<ShoppingItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchItems(items: List<ShoppingItemEntity>)

    @Query("UPDATE ShoppingItemEntity SET checked = :checked WHERE id = :id AND userId = :userId")
    suspend fun toggleItem(id: Int, checked: Boolean, userId: String)

    @Query("DELETE FROM ShoppingItemEntity WHERE id = :id AND userId = :userId")
    suspend fun deleteItem(id: Int, userId: String)

    @Query("DELETE FROM ShoppingItemEntity WHERE userId = :userId")
    suspend fun deleteAllItems(userId: String)
}
