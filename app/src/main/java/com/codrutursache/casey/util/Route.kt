package com.codrutursache.casey.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.codrutursache.casey.R

interface NavBarRoute {
    val route: String
    val icon: ImageVector

    @get:StringRes
    val labelResourceId: Int
}

sealed class Route(val route: String) {
    data object AuthRoute : Route("auth_screen")

    data object ProfileRoute : Route("profile_screen"), NavBarRoute {
        override val icon: ImageVector = Icons.Filled.AccountCircle
        override val labelResourceId: Int = R.string.profile
    }

    data object HomeRoute : Route("home_screen"), NavBarRoute {
        override val icon: ImageVector = Icons.Filled.Home
        override val labelResourceId: Int = R.string.home
    }

    data object SettingsRoute : Route("settings_screen")
}


val bottomNavItems = listOf<NavBarRoute>(Route.HomeRoute, Route.ProfileRoute)

