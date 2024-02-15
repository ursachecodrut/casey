package com.codrutursache.casey.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codrutursache.casey.navigation.NavGraph
import com.codrutursache.casey.navigation.Route
import com.codrutursache.casey.presentation.auth.AuthViewModel
import com.codrutursache.casey.presentation.core.appbars.BottomBar
import com.codrutursache.casey.presentation.core.appbars.TopBar
import com.codrutursache.casey.presentation.profile.components.ProfileBottomSheet
import com.codrutursache.casey.presentation.ui.CaseyTheme
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

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val isBottomBarVisible =
                rememberSaveable { mutableStateOf(false) }

            val sheetState = rememberModalBottomSheetState()
            var isSheetOpen by remember { mutableStateOf(false) }
            val openProfileBottomSheet = { isSheetOpen = true }
            val closeProfileBottomSheet = { isSheetOpen = false }

            isBottomBarVisible.value = when (navBackStackEntry?.destination?.route) {
                Route.AuthRoute.route -> false
                Route.ProfileRoute.route -> true
                else -> true
            }

            CaseyTheme {
                Scaffold(
                    topBar = {
                        TopBar(
                            navController = navController,
                            openProfileBottomSheet = openProfileBottomSheet,
                        )
                    },
                    bottomBar = {
                        if (isBottomBarVisible.value)
                            BottomBar(
                                navigateTo = { route ->
                                    navController.navigate(route)
                                },
                                currentRoute = navBackStackEntry?.destination?.route,
                            )
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        innerPadding = innerPadding,
                    )

                    if (isSheetOpen) {
                        ProfileBottomSheet(
                            sheetState = sheetState,
                            closeSheet = closeProfileBottomSheet,
                            navController = navController,
                        )
                    }
                }
            }

        }
    }
}