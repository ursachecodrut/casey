package com.codrutursache.casey.presentation.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.base.BottomBar
import com.codrutursache.casey.presentation.base.InfiniteGridScroll
import com.codrutursache.casey.presentation.base.topbar.RecipesTopBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.recipes.components.RecipeCard
import com.codrutursache.casey.util.mock.Mocks


@Composable
fun RecipesListScreen(
    navigateTo: (String) -> Unit,
    recipes: List<RecipeResponse>,
    fetchMoreRecipes: () -> Unit,
    navigateToRecipeInformation: (Int, String?, String?, String?) -> Unit,
) {
    Scaffold(
        topBar = { RecipesTopBar() },
        bottomBar = {
            BottomBar(
                currentRoute = Route.RecipesRoute.route,
                navigateTo = navigateTo,
            )
        }
    ) { innerPadding ->
        InfiniteGridScroll(
            itemsCount = recipes.size, loadMoreItems = fetchMoreRecipes,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            RecipeCard(
                recipe = recipes[it],
                navigateToRecipeInformation = navigateToRecipeInformation
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    RecipesListScreen(
        navigateTo = { },
        recipes = Mocks.recipeListDto.results,
        fetchMoreRecipes = { /*TODO*/ },
        navigateToRecipeInformation = { _, _, _, _ -> }
    )
}