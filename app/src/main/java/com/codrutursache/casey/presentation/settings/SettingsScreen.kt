package com.codrutursache.casey.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.codrutursache.casey.R
import com.codrutursache.casey.components.ProgressBar
import com.codrutursache.casey.domain.model.Response
import com.codrutursache.casey.domain.repository.SignOutResponse
import kotlinx.coroutines.Job

@Composable
fun SettingsScreen(
    signOutResponse: SignOutResponse,
    navigateToAuthScreen: () -> Unit,
    signOut: () -> Job,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            ElevatedButton(onClick = { signOut() }) {
                Text(text = stringResource(R.string.sign_out))
            }
        }


    }

    when (signOutResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signOutResponse.data?.let { isSignOut ->
            LaunchedEffect(isSignOut) {
                if (isSignOut) {
                    navigateToAuthScreen()
                }
            }
        }

        is Response.Failure -> LaunchedEffect(Unit) {
            signOutResponse.e.printStackTrace()
        }
    }


}