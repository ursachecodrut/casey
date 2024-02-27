package com.codrutursache.casey.presentation.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.dto.RecipeDto
import com.codrutursache.casey.presentation.base.InfiniteGridScroll
import com.codrutursache.casey.util.mock.Mocks


@Composable
fun RecipesListScreen(
    recipes: List<RecipeDto>,
    fetchMoreRecipes: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        RecipesListSuccess(recipes = recipes, fetchMoreRecipes = fetchMoreRecipes)
    }
}

@Composable
fun RecipesListSuccess(
    recipes: List<RecipeDto>,
    fetchMoreRecipes: () -> Unit,
) {

    InfiniteGridScroll(
        itemsCount = recipes.size, loadMoreItems = fetchMoreRecipes,
    ) {
        RecipeCard(recipe = recipes[it])
    }

}

@Composable
fun RecipeCard(
    recipe: RecipeDto,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f,
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.favorite)
                    )
                }

                Text(
                    text = recipe.title,
                    style = TextStyle(
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecipesListScreenPreview() {
    RecipesListScreen(
        recipes = Mocks.recipeListDto.results,
        fetchMoreRecipes = { /*TODO*/ })
}