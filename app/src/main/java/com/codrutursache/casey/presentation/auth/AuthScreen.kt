package com.codrutursache.casey.presentation.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.repository.OneTapSignInResponse
import com.codrutursache.casey.domain.repository.SignInWithIntentResponse
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.components.LoadingScreen
import kotlinx.coroutines.Job

@Composable
fun AuthScreen(
    signInWithIntentResponse: SignInWithIntentResponse,
    oneTapSignInResponse: OneTapSignInResponse,
    oneTapSignIn: () -> Job,
    signInWithIntent: (Intent?) -> Job,
    navigateToProfileScreen: () -> Unit
) {
    Scaffold() { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
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


        val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    signInWithIntent(result.data)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        when (oneTapSignInResponse) {
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> oneTapSignInResponse.data?.let { signInResult ->
                LaunchedEffect(signInResult) {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
                    launcher.launch(intentSenderRequest)
                }
            }

            is Resource.Failure -> LaunchedEffect(Unit) {
                oneTapSignInResponse.e.printStackTrace()
            }
        }

        when (signInWithIntentResponse) {
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> signInWithIntentResponse.data?.let { signedIn ->
                LaunchedEffect(signedIn) {
                    if (signedIn) {
                        navigateToProfileScreen()
                    }
                }
            }

            is Resource.Failure -> LaunchedEffect(Unit) {
                signInWithIntentResponse.e.printStackTrace()
            }
        }
    }

}