package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
