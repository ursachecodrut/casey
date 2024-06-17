package com.codrutursache.casey.presentation.shopping_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopBar(
    clearShoppingList: () -> Unit
) {

    var isMenuOpen by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.shopping_list),
                style = Typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = { isMenuOpen = true },
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )

                DropdownMenu(expanded = isMenuOpen, onDismissRequest = { isMenuOpen = false }) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.clear)) },
                        onClick = { clearShoppingList() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = stringResource(R.string.clear)
                            )
                        }
                    )
                }
            }


        }
    )
}


@Preview(name = "[EN] Shopping List Top Bar preview", locale = "en")
@Composable
fun ShoppingListTopBarPreview() {
    ShoppingListTopBar(
        clearShoppingList = {}
    )
}


@Preview(name = "[RO] Shopping List Top Bar preview", locale = "ro")
@Composable
fun ShoppingListTopBarPreviewRo() {
    ShoppingListTopBar(
        clearShoppingList = {}
    )
}
