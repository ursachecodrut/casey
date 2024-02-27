package com.codrutursache.casey.di

import com.codrutursache.casey.data.remote.repository.AuthRepositoryImpl
import com.codrutursache.casey.data.remote.repository.ProfileRepositoryImpl
import com.codrutursache.casey.data.remote.repository.RecipesRepositoryImpl
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.ProfileRepository
import com.codrutursache.casey.domain.repository.RecipesRepository
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
}