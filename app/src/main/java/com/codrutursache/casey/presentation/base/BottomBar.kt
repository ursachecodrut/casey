package com.codrutursache.casey.presentation.base

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
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.util.Route
import com.codrutursache.casey.util.bottomNavItems
import com.codrutursache.casey.presentation.theme.CaseyTheme


@Composable
fun BottomBar(
    navigateTo: (String) -> Unit,
    currentRoute: String?,
) {
    var selectedItem by remember { mutableIntStateOf(0) }

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
                    navigateTo(item.route)
                }
            )

        }
    }
}

@Preview("[EN] Bottom Bar Preview")
@Composable
private fun BottomBarPreview() {
    CaseyTheme {
        BottomBar(
            navigateTo = {},
            currentRoute = Route.HomeRoute.route
        )
    }
}

@Preview("[RO] Bottom Bar Preview", locale = "ro")
@Composable
private fun BottomBarPreviewRo() {
    CaseyTheme {
        BottomBar(
            navigateTo = {},
            currentRoute = Route.HomeRoute.route
        )
    }
}
