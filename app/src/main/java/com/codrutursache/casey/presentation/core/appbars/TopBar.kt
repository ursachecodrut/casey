package com.codrutursache.casey.presentation.core.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.codrutursache.casey.R
import com.codrutursache.casey.navigation.Route
import com.codrutursache.casey.presentation.ui.Typography

@Composable
fun TopBar(
    navController: NavHostController,
    openProfileBottomSheet: () -> Unit,
) {
    when (navController.currentDestination?.route) {
        Route.ProfileRoute.route -> ProfileTopBar(
            openProfileBottomSheet = openProfileBottomSheet,
        )
    }
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
                fontSize = Typography.titleLarge.fontSize,
                fontWeight = Typography.titleLarge.fontWeight
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
