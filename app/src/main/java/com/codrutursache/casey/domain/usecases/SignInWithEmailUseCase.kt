package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signInWithEmail(email, password)
}