package com.codrutursache.casey.domain.usecases

import com.codrutursache.casey.domain.repository.AuthRepository
import com.codrutursache.casey.domain.repository.AuthResponse
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signUpWithEmail(email, password)
}