package com.codrutursache.casey.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressBar() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}