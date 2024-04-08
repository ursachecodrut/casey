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
import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.response.RecipeResponse
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
import com.codrutursache.casey.presentation.shopping_list.ShoppingListScreen
import com.codrutursache.casey.presentation.shopping_list.ShoppingListViewModel
import com.codrutursache.casey.util.Response

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val navigateTo = { route: String -> navController.navigate(route) }
    val navigateBack = { navController.navigateBack() }

    // saved recipes viewModel
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    NavHost(
        navController = navController,
        startDestination = Route.RecipesRoute.route,
        modifier = Modifier
            .padding(innerPadding)
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
                navigateTo = navigateTo,
                recipes = recipes,
                fetchMoreRecipes = recipesListViewModel::getRecipes,
                navigateToRecipeInformation = navController::navigateToRecipeDetails
            )
        }

        composable(
            route = Route.RecipeInformationRoute.routeWithArgs,
            arguments = Route.RecipeInformationRoute.arguments
        ) { stackEntry ->
            val recipeId = stackEntry.arguments?.getInt("recipeId") ?: return@composable
            val recipeTitle = stackEntry.arguments?.getString("title") ?: return@composable
            val recipeImage = stackEntry.arguments?.getString("image") ?: return@composable
            val recipeImageType = stackEntry.arguments?.getString("imageType") ?: return@composable
            val recipe = RecipeResponse(
                id = recipeId,
                title = recipeTitle,
                image = recipeImage,
                imageType = recipeImageType,
            )

            val recipeInformationViewModel = hiltViewModel<RecipeInformationViewModel>()
            val addIngredients = { ingredients: List<ExtendedIngredientResponse>,
                                   numberOfServings: Int ->
                recipeInformationViewModel.addIngredientsToShoppingList(
                    ingredients,
                    numberOfServings
                )
            }
            val saveRecipe = { recipeInformationViewModel.saveRecipe(recipe) }
            val unsaveRecipe = { recipeInformationViewModel.unsaveRecipe(recipeId) }
            val isSavedRecipe = when (val response = profileViewModel.savedRecipesIds.value) {
                is Response.Success -> {
                    response.data?.any { r -> r.id == recipeId }
                }

                else -> false
            }

            LaunchedEffect(recipeId) {
                recipeInformationViewModel.getRecipeInformation(recipeId)
            }

            val response by remember { recipeInformationViewModel.recipeInformation }

            RecipeInformationScreen(
                response = response,
                addIngredients = addIngredients,
                saveRecipe = saveRecipe,
                unsaveRecipe = unsaveRecipe,
                isSavedRecipe = isSavedRecipe,
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )

        }

        composable(
            route = Route.ShoppingListRoute.route,
        ) {
            val shoppingListViewModel = hiltViewModel<ShoppingListViewModel>()

            ShoppingListScreen(
                response = shoppingListViewModel.shoppingList.value,
                toggleItem = shoppingListViewModel::toggleShoppingListItem,
                clearShoppingList = shoppingListViewModel::clearShoppingList,
                navigateTo = navigateTo,
            )
        }


        composable(
            route = Route.ProfileRoute.route
        ) {
            LaunchedEffect(Unit) {
                profileViewModel.getSavedRecipesIds()
            }

            val recipes by remember { profileViewModel.savedRecipesIds }

            ProfileScreen(
                navigateTo = navigateTo,
                displayName = profileViewModel.displayName,
                photoUrl = profileViewModel.photoUrl,
                recipes = recipes,
                navigateToRecipeInformation = navController::navigateToRecipeDetails,
                navigateToSettings = navController::navigateToSettings
            )
        }


        composable(
            route = Route.SettingsRoute.route
        ) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()

            SettingsScreen(
                navigateTo = navigateTo,
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

fun NavHostController.navigateToSettings() {
    navigate(Route.SettingsRoute.route)
}

fun NavHostController.navigateBack() {
    popBackStack()
}