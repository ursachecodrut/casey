package com.codrutursache.casey.di

import com.codrutursache.casey.data.repository.AuthRepositoryImpl
import com.codrutursache.casey.data.repository.ProfileRepositoryImpl
import com.codrutursache.casey.data.repository.RecipesRepositoryImpl
import com.codrutursache.casey.data.repository.ShoppingListRepositoryImpl
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.domain.repository.RecipesRepository
import com.codrutursache.casey.domain.repository.ShoppingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

    @Binds
    @Singleton
    abstract fun bindShoppingListRepository(
        shoppingListRepositoryImpl: ShoppingListRepositoryImpl
    ): ShoppingListRepository
}