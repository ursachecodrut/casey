package com.codrutursache.casey.presentation.components

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern


@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    label: String = "Email"
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
        modifier = Modifier.padding(16.dp)
    )

    if (!isEmailValid) {
        Text(
            text = "Invalid email address",
            color = Color.Red,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}


fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    return emailPattern.matcher(email).matches()
}