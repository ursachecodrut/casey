package com.codrutursache.casey.navigation

import com.codrutursache.casey.core.Constants.Routes.AUTH_SCREEN_ROUTE
import com.codrutursache.casey.core.Constants.Routes.PROFILE_SCREEN_ROUTE
import com.codrutursache.casey.core.Constants.Routes.SETTINGS_SCREEN_ROUTE

interface BottomNavRoute {
    val icon: String
}

sealed class Route(val route: String) {
    data object AuthRoute : Route(AUTH_SCREEN_ROUTE)
    data object ProfileRoute : Route(PROFILE_SCREEN_ROUTE), BottomNavRoute {
        override val icon: String = "profile"
    }

    data object SettingsRoute : Route(SETTINGS_SCREEN_ROUTE)
}