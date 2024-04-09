package com.codrutursache.casey.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.repository.SignOutResponse
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.components.ProgressBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.profile.components.ProfileTopBar
import com.codrutursache.casey.domain.model.Resource
import kotlinx.coroutines.Job

@Composable
fun SettingsScreen(
    navigateTo: (String) -> Unit,
    signOutResponse: SignOutResponse,
    navigateToAuthScreen: () -> Unit,
    signOut: () -> Job,
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                openProfileBottomSheet = { },
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Route.SettingsRoute.route,
                navigateTo = navigateTo
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            ElevatedButton(onClick = { signOut() }) {
                Text(text = stringResource(R.string.sign_out))
            }
        }

        when (signOutResponse) {
            is Resource.Loading -> ProgressBar()
            is Resource.Success -> signOutResponse.data?.let { isSignOut ->
                LaunchedEffect(isSignOut) {
                    if (isSignOut) {
                        navigateToAuthScreen()
                    }
                }
            }

            is Resource.Failure -> LaunchedEffect(Unit) {
                signOutResponse.e.printStackTrace()
            }

        }
    }
}