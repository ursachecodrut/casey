package com.codrutursache.casey.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codrutursache.casey.presentation.auth.AuthScreen
import com.codrutursache.casey.presentation.auth.AuthViewModel
import com.codrutursache.casey.presentation.profile.ProfileScreen
import com.codrutursache.casey.presentation.profile.ProfileViewModel
import com.codrutursache.casey.presentation.recipe_information.RecipeInformationScreen
import com.codrutursache.casey.presentation.recipe_information.RecipeInformationViewModel
import com.codrutursache.casey.presentation.recipes.RecipesListScreen
import com.codrutursache.casey.presentation.recipes.RecipesListViewModel
import com.codrutursache.casey.presentation.settings.SettingsScreen
import com.codrutursache.casey.presentation.settings.SettingsViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    NavHost(
        navController = navController,
        startDestination = Route.RecipesRoute.route,
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
            val authViewModel = hiltViewModel<AuthViewModel>()

            AuthScreen(
                signInWithIntentResponse = authViewModel.signInWithIntentResponse,
                oneTapSignInResponse = authViewModel.oneTapSignInResponse,
                oneTapSignIn = authViewModel::oneTapSignIn,
                signInWithIntent = authViewModel::signInWithIntent,
                navigateToProfileScreen = {
                    navController.navigate(Route.ProfileRoute.route)
                }
            )
        }

        composable(
            route = Route.RecipesRoute.route
        ) {
            val recipesListViewModel = hiltViewModel<RecipesListViewModel>()

            val recipes by remember { recipesListViewModel.recipeListDto }

            RecipesListScreen(
                recipes = recipes,
                fetchMoreRecipes = recipesListViewModel::getRecipes,
                navigateToRecipeInformation = navController::navigateToRecipeDetails
            )
        }

        composable(
            route = Route.RecipeInformationRoute.routeWithArgs,
            arguments = Route.RecipeInformationRoute.arguments
        ) {
            val recipeId = it.arguments?.getInt("recipeId") ?: return@composable
            val recipeInformationViewModel = hiltViewModel<RecipeInformationViewModel>()

            LaunchedEffect(recipeId) {
                recipeInformationViewModel.getRecipeInformation(recipeId)
            }

            val response by remember { recipeInformationViewModel.recipeInformation }

            RecipeInformationScreen(
                response = response,
            )

        }

        composable(
            route = Route.ProfileRoute.route
        ) {
            val profileViewModel = hiltViewModel<ProfileViewModel>()

            val recipes by remember { profileViewModel.savedRecipesIds }

            ProfileScreen(
                displayName = profileViewModel.displayName,
                photoUrl = profileViewModel.photoUrl,
                recipes = recipes,
                navigateToRecipeInformation = navController::navigateToRecipeDetails
            )
        }


        composable(
            route = Route.SettingsRoute.route
        ) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()

            SettingsScreen(
                signOutResponse = settingsViewModel.signOutResponse,
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Route.AuthRoute.route)
                },
                signOut = settingsViewModel::signOut,
            )
        }
    }
}

fun NavHostController.navigateToRecipeDetails(
    recipeId: Int,
    recipeTitle: String? = null,
    recipeImage: String? = null,
    recipeImageType: String? = null
) {
    val titleArg = recipeTitle?.let { "title=$it" } ?: ""
    val imageArg = recipeImage?.let { "image=$it" } ?: ""
    val imageTypeArg = recipeImageType?.let { "imageType=$it" } ?: ""
    val optionalArgs =
        listOf(titleArg, imageArg, imageTypeArg)
            .filter { it.isNotEmpty() }
            .joinToString("&")

    val route = if (optionalArgs.isNotEmpty()) {
        "${Route.RecipeInformationRoute.route}/$recipeId?$optionalArgs"
    } else {
        "${Route.RecipeInformationRoute.route}/$recipeId"
    }

    navigate(route)
}

fun NavHostController.navigateToAuth() {
    navigate(Route.AuthRoute.route)
}