package com.codrutursache.casey.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codrutursache.casey.presentation.auth.AuthScreen
import com.codrutursache.casey.presentation.profile.ProfileScreen
import com.codrutursache.casey.presentation.profile.ProfileViewModel
import com.codrutursache.casey.presentation.recipes.RecipesListScreen
import com.codrutursache.casey.presentation.recipes.RecipesListViewModel
import com.codrutursache.casey.presentation.settings.SettingsScreen
import com.codrutursache.casey.presentation.settings.SettingsViewModel
import com.codrutursache.casey.util.Route

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeRoute.route,
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
    ) {
        composable(
            route = Route.AuthRoute.route
        ) {

            AuthScreen(
                navigateToProfileScreen = {
                    navController.navigate(Route.ProfileRoute.route)
                }
            )
        }

        composable(
            route = Route.HomeRoute.route
        ) {

            val recipesListViewModel = hiltViewModel<RecipesListViewModel>()
            val recipes = recipesListViewModel.recipes

            RecipesListScreen(response = recipes)
        }

        composable(
            route = Route.ProfileRoute.route
        ) {
            val profileViewModel = hiltViewModel<ProfileViewModel>()
            val displayName = profileViewModel.displayName
            val photoUrl = profileViewModel.photoUrl

            ProfileScreen(
                displayName = displayName,
                photoUrl = photoUrl,
            )
        }


        composable(
            route = Route.SettingsRoute.route
        ) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            val signOutResponse = settingsViewModel.signOutResponse
            val signOut = settingsViewModel::signOut

            SettingsScreen(
                signOutResponse = signOutResponse,
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Route.AuthRoute.route)
                },
                signOut = signOut,
            )
        }
    }
}