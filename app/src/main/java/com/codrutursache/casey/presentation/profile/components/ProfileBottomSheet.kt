package com.codrutursache.casey.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    sheetState: SheetState,
    closeSheet: () -> Unit,
    navigateToSettings: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            ListItem(
                headlineContent = {
                    Text(text = stringResource(R.string.settings))
                },
                leadingContent = {
                    Icon(Icons.Filled.Settings, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    navigateToSettings()
                    closeSheet()
                }
            )
        }
    }
}