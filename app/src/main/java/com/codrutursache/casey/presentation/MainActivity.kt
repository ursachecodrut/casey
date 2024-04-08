package com.codrutursache.casey.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.codrutursache.casey.presentation.auth.AuthViewModel
import com.codrutursache.casey.presentation.navigation.NavGraph
import com.codrutursache.casey.presentation.theme.CaseyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val authViewModel = hiltViewModel<AuthViewModel>()

            LaunchedEffect(Unit) {
                authViewModel.checkAuth(navController)
            }

            CaseyTheme {
                NavGraph(navController = navController)
            }
        }
    }
}

