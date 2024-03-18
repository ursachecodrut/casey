package com.codrutursache.casey.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codrutursache.casey.domain.model.ShoppingItemEntity

@Database(
    entities = [ShoppingItemEntity::class],
    version = 1
)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract val shoppingListDao: ShoppingListDao

    companion object {
        const val DATABASE_NAME = "shopping_list_database"
    }
}