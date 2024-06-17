package com.codrutursache.casey.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import java.util.regex.Pattern


@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    label: String = "Email",
    modifier: Modifier = Modifier
) {
    var isEmailValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
            isEmailValid = isValidEmail(it)
        },
        label = { Text(label) },
        singleLine = true,
        isError = !isEmailValid,
        supportingText = {
            Row {
                Text(
                    if (!isEmailValid) stringResource(R.string.invalid_email_address) else "",
                    Modifier.clearAndSetSemantics {})
            }
        },
        keyboardActions = KeyboardActions { isValidEmail(email) },
        modifier = modifier.semantics {
            // Provide localized description of the error
            if (!isEmailValid) {
                this.contentDescription = "Invalid email address"
            }
        }
    )
}


fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    return emailPattern.matcher(email).matches()
}