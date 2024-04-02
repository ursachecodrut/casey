package com.codrutursache.casey.presentation.base.topbar

import android.os.Bundle
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesTopBar() {
    TopAppBar(title = {
        Text(text = stringResource(R.string.recipes))
    })
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

@Preview(name = "[EN] Top Bar preview", locale = "en")
@Composable
fun RecipesTopBarPreview() {
    RecipesTopBar()
}

@Preview(name = "[RO] Top Bar preview", locale = "ro")
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
