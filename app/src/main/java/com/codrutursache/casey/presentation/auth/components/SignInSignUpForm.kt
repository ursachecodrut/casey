package com.codrutursache.casey.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.components.EmailTextField
import com.codrutursache.casey.presentation.components.PasswordTextField
import kotlinx.coroutines.Job

@Composable
fun SignInSignUpForm(
    isSignIn: Boolean,
    authWithEmail: (String, String) -> Unit,
    oneTapSignIn: () -> Job,
    navigateToSignInScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 8.dp)
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val actionText = if (isSignIn) stringResource(R.string.sign_in) else stringResource(R.string.sign_up)

        Text(
            text = actionText,
            fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
        )

        EmailTextField(
            email = email,
            onEmailChange = { email = it },
            modifier = Modifier.fillMaxWidth()
        )

        PasswordTextField(
            password = password,
            onPasswordChange = { password = it },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { authWithEmail(email, password) },
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = actionText,
                modifier = Modifier.padding(6.dp),
                fontSize = 18.sp
            )
        }

        SignInSignUpRedirect(
            isSignIn = isSignIn,
            navigateToSignUp = navigateToSignUpScreen,
            navigateToSignIn = navigateToSignInScreen,
        )

        Text(text = "OR", modifier = Modifier.padding(8.dp))
        Divider()

        OutlinedButton(
            shape = RoundedCornerShape(6.dp),
            onClick = { oneTapSignIn() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.sign_in_with_google),
                modifier = Modifier.padding(6.dp),
                fontSize = 18.sp
            )
        }
    }
}