package com.codrutursache.casey.domain.usecases

import android.content.Intent
import com.codrutursache.casey.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    fun isAuthenticated() = authRepository.isUserAuthenticatedInFirebase

    suspend fun signInWithIntent(intent: Intent) = authRepository.signInWithIntent(intent)

    suspend fun googleOneTapSignIn() = authRepository.googleOneTapSignIn()
}