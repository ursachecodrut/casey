package com.codrutursache.casey.presentation.recipes.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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

