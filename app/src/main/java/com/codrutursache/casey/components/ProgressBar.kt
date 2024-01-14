package com.codrutursache.casey.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProgressBar() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Text("Loading")
    }
}