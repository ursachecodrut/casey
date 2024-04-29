package com.codrutursache.casey.presentation.components.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    icon: ImageVector?,
    title: String? = null,
    description: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {
            icon?.let {
                Icon(imageVector = it, contentDescription = null)
            }
        },
        title = {
            Text(text = title ?: "Permission Request")
        },
        text = { Text(description) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "Allow")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Dismiss")
            }
        },
        onDismissRequest = onDismiss,
        modifier = modifier
    )
}