package com.codrutursache.casey.presentation.auth.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInSignUpRedirect(
    isSignIn: Boolean,
    navigateToSignUp: () -> Unit,
    navigateToSignIn: () -> Unit,
) {
    val infoText = if (isSignIn) "Don't have an account?" else "Already have an account?"
    val navigate = if (isSignIn) navigateToSignUp else navigateToSignIn
    val goToText = if (isSignIn) "Sign up" else "Sign in"

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { navigate() }
    ) {
        Text(
            text = infoText,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Text(
            text = goToText,
            color = MaterialTheme.colorScheme.inversePrimary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.clickable {
                navigate()
                if (isSignIn) {
                    Log.d("SignInSignUpRedirect", "Navigating to sign up screen")
                } else {
                    Log.d("SignInSignUpRedirect", "Navigating to sign in screen")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpRedirectPreview() {
    SignInSignUpRedirect(
        isSignIn = true,
        navigateToSignUp = { /*TODO*/ },
        navigateToSignIn = { /*TODO*/ }
    )
}
@Preview(showBackground = true)
@Composable
fun SignInRedirectPreview() {
    SignInSignUpRedirect(
        isSignIn = false,
        navigateToSignUp = { /*TODO*/ },
        navigateToSignIn = { /*TODO*/ }
    )
}
