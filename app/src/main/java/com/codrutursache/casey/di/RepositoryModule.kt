package com.codrutursache.casey.di

import com.codrutursache.casey.data.repository.AuthRepositoryImpl
import com.codrutursache.casey.data.repository.ProfileRepositoryImpl
import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.ProfileRepository
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
}