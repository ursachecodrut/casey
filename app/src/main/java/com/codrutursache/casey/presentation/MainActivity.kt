package com.codrutursache.casey.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codrutursache.casey.presentation.auth.AuthViewModel
import com.codrutursache.casey.presentation.base.BottomBar
import com.codrutursache.casey.presentation.base.topbar.TopBar
import com.codrutursache.casey.presentation.base.topbar.TopBarViewModel
import com.codrutursache.casey.presentation.navigation.NavGraph
import com.codrutursache.casey.presentation.profile.components.ProfileBottomSheet
import com.codrutursache.casey.presentation.shopping_list.ShoppingListViewModel
import com.codrutursache.casey.presentation.theme.CaseyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
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

