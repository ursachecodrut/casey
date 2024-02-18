package com.codrutursache.casey.presentation.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.base.ProgressBar
import com.codrutursache.casey.data.remote.model.Response
import java.lang.Exception

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Button(
                modifier = Modifier.padding(bottom = 48.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = { viewModel.oneTapSignIn() }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                Text(
                    text = stringResource(R.string.sign_in_with_google),
                    modifier = Modifier.padding(6.dp),
                    fontSize = 18.sp
                )
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                viewModel.signInWithIntent(result.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> oneTapSignInResponse.data?.let { signInResult ->
            LaunchedEffect(signInResult) {
                val intentSenderRequest =
                    IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
                launcher.launch(intentSenderRequest)
            }
        }

        is Response.Failure -> LaunchedEffect(Unit) {
            oneTapSignInResponse.e.printStackTrace()
        }
    }

    when (val signInWithGoogleResponse = viewModel.signInWithIntentResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                if (signedIn) {
                    navigateToProfileScreen()
                }
            }
        }

        is Response.Failure -> LaunchedEffect(Unit) {
            signInWithGoogleResponse.e.printStackTrace()
        }
    }
}