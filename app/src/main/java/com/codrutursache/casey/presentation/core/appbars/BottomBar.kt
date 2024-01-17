package com.codrutursache.casey.presentation.core.appbars

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codrutursache.casey.navigation.bottomNavItems


@Composable
fun BottomBar(
    navController: NavController,
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentRouteIndex = bottomNavItems.indexOfFirst { it.route == currentRoute }

    if (currentRouteIndex != -1) {
        selectedItem = currentRouteIndex
    }

    NavigationBar {
        bottomNavItems.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            val label = stringResource(item.labelResourceId)
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = label
                    )
                },
                label = { Text(text = label) },
                selected = isSelected,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                }
            )

        }
    }
}