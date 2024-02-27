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
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    private val isUserAuthenticated get() = signInUseCase.isAuthenticated()

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Success(null))
        private set
    var signInWithIntentResponse by mutableStateOf<SignInWithIntentResponse>(Response.Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        oneTapSignInResponse = signInUseCase.googleOneTapSignIn()
    }

    fun signInWithIntent(intent: Intent?) = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        signInWithIntentResponse = signInUseCase.signInWithIntent(intent ?: return@launch)
    }

    fun checkAuth(navController: NavHostController) {
        viewModelScope.launch {
            if (!isUserAuthenticated) {
                navController.navigate(Route.AuthRoute.route)
            }
        }
    }
}

