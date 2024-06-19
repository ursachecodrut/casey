package com.codrutursache.casey.presentation.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.components.InfiniteGridScroll
import com.codrutursache.casey.presentation.components.SearchBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.recipes.components.RecipeCard
import com.codrutursache.casey.presentation.recipes.components.RecipesTopBar
import com.codrutursache.casey.util.mock.Mocks


@Composable
fun RecipesListScreen(
    navigateTo: (String) -> Unit,
    recipes: List<RecipeResponse>,
    fetchMoreRecipes: () -> Unit,
    searchRecipe: (String) -> Unit,
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
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            SearchBar(
                onSearch = { searchRecipe(it) },
                hint = stringResource(R.string.search_for_recipes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            InfiniteGridScroll(
                itemsCount = recipes.size,
                loadMoreItems = fetchMoreRecipes,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("recipes_lazy_grid")
            ) {
                RecipeCard(
                    recipe = recipes[it],
                    navigateToRecipeInformation = navigateToRecipeInformation
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    RecipesListScreen(
        navigateTo = { },
        recipes = Mocks.recipeListDto.results,
        fetchMoreRecipes = { },
        searchRecipe = { },
        navigateToRecipeInformation = { _, _, _, _ -> }
    )
}