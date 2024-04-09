package com.codrutursache.casey.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.components.InfiniteGridScroll
import com.codrutursache.casey.presentation.recipes.components.RecipeCard
import com.codrutursache.casey.util.mock.Mocks


@Composable
fun SavedRecipeList(
    recipes: List<RecipeResponse>,
    navigateToRecipeInformation: (Int, String?, String?, String?) -> Unit
) {
    InfiniteGridScroll(
        itemsCount = recipes.size,
        isLazy = false,
    ) {
        RecipeCard(
            recipe = recipes[it],
            navigateToRecipeInformation = navigateToRecipeInformation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesListPreview() {
    SavedRecipeList(
        recipes = listOf(
            Mocks.recipeResponse,
            Mocks.recipeResponse,
            Mocks.recipeResponse,
        ),
    ) { _, _, _, _ -> }
}
