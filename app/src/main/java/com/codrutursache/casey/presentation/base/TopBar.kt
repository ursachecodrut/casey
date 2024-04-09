package com.codrutursache.casey.presentation.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.recipes),
                style = Typography.titleLarge
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInformationTopBar(
    goBack: () -> Unit,
    isSavedRecipe: Boolean? = false,
    saveRecipe: () -> Unit,
    unsaveRecipe: () -> Unit,
) {
    var isSaved by remember { mutableStateOf(isSavedRecipe ?: false) }

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
            val icon: ImageVector =
                if (isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            val onClickAction = if (isSaved) unsaveRecipe else saveRecipe
            IconButton(onClick = {
                onClickAction()
                isSaved = !isSaved
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(R.string.favorite)
                )
            }
        },
    )
}

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
                        text = { Text(text = stringResource(R.string.edit)) },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = stringResource(R.string.edit)
                            )
                        }

                    )
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

@Preview(name = "[EN] Recipes Top Bar preview", locale = "en")
@Composable
fun RecipesTopBarPreview() {
    RecipesTopBar()
}

@Preview(name = "[RO] Recipes Top Bar preview", locale = "ro")
@Composable
fun RecipesTopBarPreviewRo() {
    RecipesTopBar()
}

@Preview(name = "[EN] Recipe Information Top Bar preview", locale = "en")
@Composable
fun RecipeInformationTopBarPreview() {
    RecipeInformationTopBar(
        goBack = {},
        saveRecipe = {},
        unsaveRecipe = {},
    )
}

@Preview(name = "[RO] Recipe Information Top Bar preview", locale = "ro")
@Composable
fun RecipeInformationTopBarPreviewRo() {
    RecipeInformationTopBar(
        goBack = {},
        saveRecipe = {},
        unsaveRecipe = {},
    )
}

@Preview(showBackground = true, name = "[EN] Shopping List Top Bar preview", locale = "en")
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

@Preview(name = "[EN] Profile Top Bar preview", locale = "en")
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(openProfileBottomSheet = {})
}

@Preview(name = "[RO] Profile Top Bar preview", locale = "ro")
@Composable
fun ProfileTopBarPreviewRo() {
    ProfileTopBar(openProfileBottomSheet = {})
}
