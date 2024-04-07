package com.codrutursache.casey.presentation.base.topbar

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun TopBar(
    currentRoute: String?,
    arguments: Bundle?,
    goBack: () -> Unit,
    openProfileBottomSheet: () -> Unit,
    saveRecipe: (RecipeResponse) -> Unit,
) {
    when (currentRoute) {
        Route.ProfileRoute.route -> {
            ProfileTopBar(
                openProfileBottomSheet = openProfileBottomSheet,
            )
        }

        Route.RecipeInformationRoute.routeWithArgs -> {
            val recipeId = arguments?.getInt("recipeId")
            val recipeTitle = arguments?.getString("title")
            val recipeImage = arguments?.getString("image")
            val recipeImageType = arguments?.getString("imageType")
            val recipe = RecipeResponse(
                id = recipeId ?: 0,
                title = recipeTitle ?: "",
                image = recipeImage ?: "",
                imageType = recipeImageType ?: "",
            )


            RecipeInformationTopBar(
                goBack = goBack,
                saveRecipe = {
                    saveRecipe(recipe)
                },
            )
        }

        Route.RecipesRoute.route -> {
            RecipesTopBar()
        }

        Route.ShoppingListRoute.route -> {
            ShoppingListTopBar()
        }
    }
}

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
    saveRecipe: () -> Unit,
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
            IconButton(onClick = { saveRecipe() }) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopBar(
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
                        onClick = { },
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
    )
}

@Preview(name = "[RO] Recipe Information Top Bar preview", locale = "ro")
@Composable
fun RecipeInformationTopBarPreviewRo() {
    RecipeInformationTopBar(
        goBack = {},
        saveRecipe = {},
    )
}

@Preview(showBackground = true, name = "[EN] Shopping List Top Bar preview", locale = "en")
@Composable
fun ShoppingListTopBarPreview() {
    ShoppingListTopBar()
}


@Preview(name = "[EN] Shopping List Top Bar preview", locale = "en")
@Composable
fun ShoppingListTopBarPreviewRo() {
    ShoppingListTopBar()
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
