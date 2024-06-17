package com.codrutursache.casey.presentation.navigation

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.auth.AuthViewModel
import com.codrutursache.casey.presentation.auth.SignInScreen
import com.codrutursache.casey.presentation.auth.SignUpScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
            route = Route.SignInRoute.route
        ) {
            val authViewModel = hiltViewModel<AuthViewModel>()

            SignInScreen(
                authResponse = authViewModel.authResource,
                oneTapSignInResponse = authViewModel.oneTapSignInResource,
                oneTapSignIn = authViewModel::oneTapSignIn,
                signInWithIntent = authViewModel::signInWithIntent,
                signInWithEmail = authViewModel::signInWithEmail,
                navigateToProfileScreen = navController::navigateToProfile,
                navigateToSignUpScreen = navController::navigateToSignUp,
                navigateToSignInScreen = navController::navigateToSignIn
            )
        }

        composable(
            route = Route.SignUpRoute.route
        ) {
            val authViewModel = hiltViewModel<AuthViewModel>()

            SignUpScreen(
                authResponse = authViewModel.authResource,
                oneTapSignInResponse = authViewModel.oneTapSignInResource,
                oneTapSignIn = authViewModel::oneTapSignIn,
                signInWithIntent = authViewModel::signInWithIntent,
                signUpWithEmail = authViewModel::signUpWithEmail,
                navigateToProfileScreen = navController::navigateToProfile,
                navigateToSignUpScreen = navController::navigateToSignUp,
                navigateToSignInScreen = navController::navigateToSignIn
            )
        }

        composable(
            route = Route.RecipesRoute.route
        ) {
            val recipesListViewModel = hiltViewModel<RecipesListViewModel>()

            val recipes by remember { recipesListViewModel.recipeListDto }

            // request notification permission
            val notificationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    if (isGranted) {
                        Log.d("PermissionResult", "Notification permission granted")
                    } else {
                        Log.d("PermissionResult", "Notification permission denied")
                    }
                }
            )

            LaunchedEffect(Unit) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

            RecipesListScreen(
                navigateTo = navigateTo,
                recipes = recipes,
                fetchMoreRecipes = recipesListViewModel::getRecipes,
                searchRecipe = recipesListViewModel::searchRecipes,
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

            Log.d("RecipeInformationRoute", "Recipe id: $recipeId")

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
                is Resource.Success -> {
                    response.data?.any { r -> r.id == recipeId }
                }

                else -> false
            }

            LaunchedEffect(recipeId) {
                recipeInformationViewModel.getRecipeInformation(recipeId)
            }

            val response by remember { recipeInformationViewModel.recipeInformation }

            RecipeInformationScreen(
                resource = response,
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
                resource = shoppingListViewModel.shoppingList.value,
                toggleItem = shoppingListViewModel::toggleShoppingListItem,
                clearShoppingList = shoppingListViewModel::clearShoppingList,
                updateShoppingListItem = shoppingListViewModel::updateShoppingListItem,
                deleteShoppingListItem = shoppingListViewModel::deleteShoppingListItem,
                addShoppingItem = shoppingListViewModel::addItemToShoppingList,
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
                email = profileViewModel.email,
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
                signOutResponse = settingsViewModel.signOutResource,
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(Route.SignInRoute.route)
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

fun NavHostController.navigateToSignIn() = navigate(Route.SignInRoute.route)
fun NavHostController.navigateToSignUp() = navigate(Route.SignUpRoute.route)
fun NavHostController.navigateToSettings() = navigate(Route.SettingsRoute.route)
fun NavHostController.navigateToProfile() = navigate(Route.ProfileRoute.route)
fun NavHostController.navigateBack() {
    popBackStack()
}
