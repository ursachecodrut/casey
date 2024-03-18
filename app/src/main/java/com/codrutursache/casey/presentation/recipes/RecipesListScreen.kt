package com.codrutursache.casey.presentation.recipes

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.base.InfiniteGridScroll
import com.codrutursache.casey.presentation.recipes.components.RecipeCard
import com.codrutursache.casey.util.mock.Mocks


@Composable
fun RecipesListScreen(
    recipes: List<RecipeResponse>,
    fetchMoreRecipes: () -> Unit,
    navigateToRecipeInformation: (Int, String?, String?, String?) -> Unit,
) {
    InfiniteGridScroll(
        itemsCount = recipes.size, loadMoreItems = fetchMoreRecipes,
    ) {
        RecipeCard(
            recipe = recipes[it],
            navigateToRecipeInformation = navigateToRecipeInformation
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    RecipesListScreen(
        recipes = Mocks.recipeListDto.results,
        fetchMoreRecipes = { /*TODO*/ },
        navigateToRecipeInformation = { _, _, _, _ -> }
    )
}