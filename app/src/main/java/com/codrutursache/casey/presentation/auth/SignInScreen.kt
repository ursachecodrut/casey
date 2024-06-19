package com.codrutursache.casey.presentation.auth

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.repository.AuthResponse
import com.codrutursache.casey.domain.repository.OneTapSignInResponse
import com.codrutursache.casey.presentation.auth.components.ErrorScreen
import com.codrutursache.casey.presentation.auth.components.SignInSignUpForm
import com.codrutursache.casey.presentation.components.LoadingScreen
import com.codrutursache.casey.presentation.theme.logo
import kotlinx.coroutines.Job

@Composable
fun SignInScreen(
    authResponse: AuthResponse,
    oneTapSignInResponse: OneTapSignInResponse,
    oneTapSignIn: () -> Job,
    signInWithIntent: (Intent?) -> Job,
    signInWithEmail: (String, String) -> Unit,
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    afterAuthNavigateTo: () -> Unit,
) {
    Scaffold(
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                authResponse is Resource.Loading || oneTapSignInResponse is Resource.Loading -> {
                    LoadingScreen()
                }

                authResponse is Resource.Failure || oneTapSignInResponse is Resource.Failure -> {
                    ErrorScreen(
                        errorMessage = (authResponse as? Resource.Failure)?.e?.localizedMessage
                            ?: (oneTapSignInResponse as? Resource.Failure)?.e?.localizedMessage
                            ?: "Unknown error"
                    )
                }


                else -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Casey",
                            fontFamily = logo.fontFamily,
                            fontSize = logo.fontSize,
                        )

                        SignInSignUpForm(
                            isSignIn = true,
                            authWithEmail = signInWithEmail,
                            oneTapSignIn = oneTapSignIn,
                            navigateToSignInScreen = navigateToSignInScreen,
                            navigateToSignUpScreen = navigateToSignUpScreen,
                        )
                    }
                }
            }
        }
    }


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                signInWithIntent(result.data)
            }
        }

    LaunchedEffect(oneTapSignInResponse) {
        if (oneTapSignInResponse is Resource.Success) {
            oneTapSignInResponse.data?.let { signInResult ->
                val intentSenderRequest =
                    IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
                launcher.launch(intentSenderRequest)
            }
        }
    }

    LaunchedEffect(authResponse) {
        if (authResponse is Resource.Success && authResponse.data == true) {
            afterAuthNavigateTo()
        }
    }
}


