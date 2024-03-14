package com.codrutursache.casey.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.presentation.base.InfiniteGridScroll
import com.codrutursache.casey.presentation.recipes.components.RecipeCard
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.Constants.MEDIUM_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.util.Constants.SMALL_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.util.Response
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun ProfileScreen(
    displayName: String?,
    photoUrl: String?,
    recipes: Response<List<RecipeResponse>>,
    navigateToRecipeInformation: (Int, String?, String?, String?) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            photoUrl?.replace(
                                SMALL_FIREBASE_IMAGE_TAG,
                                MEDIUM_FIREBASE_IMAGE_TAG
                            )
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.profile_picture),
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = displayName ?: stringResource(R.string.no_name),
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyMedium.fontSize,
                letterSpacing = Typography.bodyMedium.letterSpacing,
                lineHeight = Typography.bodyMedium.lineHeight
            )

            Divider()

            when (recipes) {
                is Response.Loading -> {
                    Text(text = "Loading...")
                }

                is Response.Success -> {
                    recipes.data?.let { recipes ->
                        InfiniteGridScroll(
                            itemsCount = recipes.size,
                            columns = 2,
                            isLazy = false,
                        ) {
                            RecipeCard(
                                recipe = recipes[it],
                                navigateToRecipeInformation = navigateToRecipeInformation
                            )
                        }

                    }
                }

                is Response.Failure -> {
                    Text(text = stringResource(R.string.something_went_wrong))
                }
            }

        }
    }
}

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