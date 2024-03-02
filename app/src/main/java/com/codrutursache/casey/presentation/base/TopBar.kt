package com.codrutursache.casey.presentation.base

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun TopBar(
    navController: NavHostController,
    openProfileBottomSheet: () -> Unit,
) {
    when (navController.currentDestination?.route) {
        Route.ProfileRoute.route -> {
            ProfileTopBar(
                openProfileBottomSheet = openProfileBottomSheet,
            )
        }

        Route.RecipeInformationRoute.routeWithArgs -> {
            RecipeInformationTopBar(
                goBack = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInformationTopBar(
    goBack: () -> Unit,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = stringResource(R.string.favorite)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    openProfileBottomSheet: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.my_profile_title),
                style = Typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = openProfileBottomSheet) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
    )
}
