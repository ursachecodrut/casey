package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.presentation.base.ProgressBar
import com.codrutursache.casey.presentation.recipe_information.components.IngredientCard
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.Response
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun RecipeInformationScreen(
    response: Response<RecipeInformationResponse>,
    popBackStack: () -> Unit,
) {

    when (response) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            response.data?.let {
                RecipeInformationSuccessScreen(recipeInfo = it, popBackStack = popBackStack)
            }
        }

        is Response.Failure -> {

            Text(text = response.e.toString())
        }
    }
}

@Composable
fun RecipeInformationSuccessScreen(
    recipeInfo: RecipeInformationResponse,
    popBackStack: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = stringResource(id = R.string.favorite)
                    )
                }
            }

            Text(
                text = recipeInfo.title,
                style = Typography.titleLarge,
                modifier = Modifier.padding(end = 64.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipeInfo.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.recipe_placeholder),
                contentDescription = recipeInfo.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6))
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.ingredients),
                    style = Typography.bodyLarge,
                )

                Text(
                    text = stringResource(
                        R.string.items,
                        recipeInfo.extendedIngredientResponses.size
                    ),
                    style = Typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                recipeInfo.extendedIngredientResponses.forEach { ingredient ->
                    IngredientCard(ingredient = ingredient)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeInformationSuccessScreenPreview() {
    RecipeInformationSuccessScreen(recipeInfo = Mocks.recipeInfoMock, popBackStack = { })

}

@Preview(showBackground = true, locale = "ro")
@Composable
fun RecipeInformationSuccessScreenPreview_RO() {
    RecipeInformationSuccessScreen(recipeInfo = Mocks.recipeInfoMock, popBackStack = { })

}
