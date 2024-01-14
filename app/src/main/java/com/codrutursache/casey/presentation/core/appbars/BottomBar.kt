package com.codrutursache.casey.presentation.core.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomBar(
    navController: NavController,
) {
    BottomAppBar(actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Check, contentDescription = "Check")
        }
    })
}