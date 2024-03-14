package com.codrutursache.casey.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.codrutursache.casey.R

interface NavBarRoute {
    val route: String
    val icon: ImageVector

    @get:StringRes
    val labelResourceId: Int
}

interface RouteWithArgs {
    val routeWithArgs: String
    val arguments: List<NamedNavArgument>
}

sealed class Route(val route: String) {
    data object AuthRoute : Route("auth_screen")

    data object ProfileRoute : Route("profile_screen"), NavBarRoute {
        override val icon: ImageVector = Icons.Filled.AccountCircle
        override val labelResourceId: Int = R.string.profile
    }

    data object RecipesRoute : Route("recipes_screen"), NavBarRoute {
        override val icon: ImageVector = Icons.Filled.Home
        override val labelResourceId: Int = R.string.home
    }

    data object RecipeInformationRoute : Route("recipe_information_screen"), RouteWithArgs {
        private const val recipeIdArg = "recipeId"
        private const val recipeTitle = "title"
        private const val recipeImage = "image"
        private const val recipeImageType = "imageType"
        override val routeWithArgs =
            "$route/{$recipeIdArg}?title={$recipeTitle}&image={$recipeImage}&imageType={$recipeImageType}"
        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(recipeIdArg) {
                type = NavType.IntType
                nullable = false
            },
            navArgument(recipeTitle) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(recipeImage) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(recipeImageType) {
                type = NavType.StringType
                nullable = true
            }
        )
    }

    data object SettingsRoute : Route("settings_screen")
}


val bottomNavItems = listOf<NavBarRoute>(Route.RecipesRoute, Route.ProfileRoute)

