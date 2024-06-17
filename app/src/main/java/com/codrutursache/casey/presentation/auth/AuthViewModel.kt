package com.codrutursache.casey.presentation.auth

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.codrutursache.casey.domain.repository.OneTapSignInResponse
import com.codrutursache.casey.domain.repository.SignInWithIntentResponse
import com.codrutursache.casey.domain.usecases.SignInUseCase
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.usecases.SignInWithEmailUseCase
import com.codrutursache.casey.domain.usecases.SignUpWithEmailUseCase
import com.codrutursache.casey.presentation.navigation.navigateToSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
) : ViewModel() {
    private val isUserAuthenticated get() = signInUseCase.isAuthenticated()

    var oneTapSignInResource by mutableStateOf<OneTapSignInResponse>(Resource.Success(null))
        private set
    var authResource by mutableStateOf<SignInWithIntentResponse>(Resource.Success(false))
        private set

    fun signInWithEmail(email: String, password: String) = viewModelScope.launch {
        authResource = Resource.Loading
        authResource = signInWithEmailUseCase(email, password)
    }

    fun signUpWithEmail(email: String, password: String) = viewModelScope.launch {
        authResource = Resource.Loading
        authResource = signUpWithEmailUseCase(email, password)
    }

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResource = Resource.Loading
        oneTapSignInResource = signInUseCase.googleOneTapSignIn()
    }

    fun signInWithIntent(intent: Intent?) = viewModelScope.launch {
        oneTapSignInResource = Resource.Loading
        authResource = signInUseCase.signInWithIntent(intent ?: return@launch)
    }

    fun checkAuth(navController: NavHostController) {
        viewModelScope.launch {
            if (!isUserAuthenticated && navController.currentDestination?.route != "sign_up_screen") {
                navController.navigateToSignIn()
            }
        }
    }
}

