package com.codrutursache.casey.di

import android.app.Application
import androidx.room.Room
import com.codrutursache.casey.data.data_source.ShoppingListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideShoppingListDatabase(app: Application): ShoppingListDatabase = Room
        .databaseBuilder(
            app,
            ShoppingListDatabase::class.java,
            ShoppingListDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration() // This is not recommended for production apps
        // and should be replaced with a proper migration strategy
        .build()

    @Provides
    @Singleton
    fun provideShoppingListDao(database: ShoppingListDatabase) = database.shoppingListDao()
}