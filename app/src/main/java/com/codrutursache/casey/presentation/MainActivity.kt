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
            val topBarViewModel = hiltViewModel<TopBarViewModel>()

            LaunchedEffect(Unit) {
                authViewModel.checkAuth(navController)
            }

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val sheetState = rememberModalBottomSheetState()
            var isSheetOpen by remember { mutableStateOf(false) }
            val openProfileBottomSheet = { isSheetOpen = true }
            val closeProfileBottomSheet = { isSheetOpen = false }


            CaseyTheme {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentRoute = navBackStackEntry?.destination?.route,
                            arguments = navBackStackEntry?.arguments,
                            goBack = { navController.popBackStack() },
                            openProfileBottomSheet = openProfileBottomSheet,
                            saveRecipe = topBarViewModel::saveRecipe,
                        )
                    },
                    bottomBar = {
                        BottomBar(
                            navigateTo = { route ->
                                navController.navigate(route)
                            },
                            currentRoute = navBackStackEntry?.destination?.route,
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavGraph(
                            navController = navController,
                            innerPadding = innerPadding,
                        )
                    }

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

