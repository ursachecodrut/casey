package com.codrutursache.casey.presentation.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.repository.OneTapSignInResponse
import com.codrutursache.casey.domain.repository.SignInWithIntentResponse
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.repository.AuthResponse
import com.codrutursache.casey.presentation.components.EmailTextField
import com.codrutursache.casey.presentation.components.LoadingScreen
import com.codrutursache.casey.presentation.components.PasswordTextField
import kotlinx.coroutines.Job

@Composable
fun AuthScreen(
    authResponse: AuthResponse,
    oneTapSignInResponse: OneTapSignInResponse,
    oneTapSignIn: () -> Job,
    signInWithIntent: (Intent?) -> Job,
    signInWithEmail: (String, String) -> Unit,
    signUpWithEmail: (String, String) -> Unit,
    navigateToProfileScreen: () -> Unit
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
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        var email by remember { mutableStateOf("") }
                        var password by remember { mutableStateOf("") }

                        EmailTextField(
                            email = email,
                            onEmailChange = { email = it }
                        )

                        PasswordTextField(
                            password = password,
                            onPasswordChange = { password = it }
                        )

                        Button(
                            onClick = { signInWithEmail(email, password) }
                        ) {
                            Text(
                                text = "Sign in",
                                modifier = Modifier.padding(6.dp),
                                fontSize = 18.sp
                            )
                        }

                        Button(
                            onClick = { signUpWithEmail(email, password) }
                        ) {
                            Text(
                                text = "Sign up",
                                modifier = Modifier.padding(6.dp),
                                fontSize = 18.sp
                            )
                        }

                        Divider()

                        Button(
                            modifier = Modifier.padding(bottom = 48.dp),
                            shape = RoundedCornerShape(6.dp),
                            onClick = { oneTapSignIn() }
                        ) {
                            Text(
                                text = stringResource(R.string.sign_in_with_google),
                                modifier = Modifier.padding(6.dp),
                                fontSize = 18.sp
                            )
                        }

                    }
                }


            }
        }
    }


    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            signInWithIntent(result.data)
        }
    }

    LaunchedEffect(oneTapSignInResponse) {
        if (oneTapSignInResponse is Resource.Success) {
            oneTapSignInResponse.data?.let { signInResult ->
                val intentSenderRequest = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
                launcher.launch(intentSenderRequest)
            }
        }
    }

    LaunchedEffect(authResponse) {
        if (authResponse is Resource.Success && authResponse.data == true) {
            navigateToProfileScreen()
        }
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage, color = Color.Red)
    }
}