package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.presentation.components.BottomBar
import com.codrutursache.casey.presentation.navigation.Route
import com.codrutursache.casey.presentation.recipe_information.components.RecipeInfoTabs
import com.codrutursache.casey.presentation.recipe_information.components.RecipeInformationTopBar
import com.codrutursache.casey.presentation.recipe_information.components.Servings
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.Response
import com.codrutursache.casey.util.mock.Mocks
import kotlinx.coroutines.Job

@Composable
fun RecipeInformationScreen(
    response: Response<RecipeInformationResponse>,
    addIngredients: (List<ExtendedIngredientResponse>, Int) -> Unit,
    saveRecipe: () -> Job,
    unsaveRecipe: () -> Job,
    isSavedRecipe: Boolean?,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
) {


    Scaffold(
        topBar = {
            RecipeInformationTopBar(
                goBack = navigateBack,
                isSavedRecipe = isSavedRecipe,
                saveRecipe = { saveRecipe() },
                unsaveRecipe = { unsaveRecipe() },
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Route.RecipeInformationRoute.route,
                navigateTo = navigateTo
            )
        },
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)

        ) {
            when (response) {
                is Response.Loading -> {
                    Text(text = "Loading...")
                }

                is Response.Success -> {
                    response.data?.let {
                        RecipeInformationSuccessScreen(
                            recipeInfo = it,
                            addIngredients = addIngredients
                        )
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
fun RecipeInformationSuccessScreen(
    recipeInfo: RecipeInformationResponse,
    addIngredients: (List<ExtendedIngredientResponse>, Int) -> Unit
) {
    // scroll state
    val scrollState = rememberScrollState()

    // servings state
    var numberOfServings by remember { mutableIntStateOf(1) }
    val increment: () -> Unit = { numberOfServings++ }
    val decrement: () -> Unit = { if (numberOfServings > 1) numberOfServings-- }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
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


            Servings(
                numberOfServings = numberOfServings,
                increment = increment,
                decrement = decrement,
            )

            ElevatedButton(
                onClick = {
                    addIngredients(recipeInfo.extendedIngredientResponses, numberOfServings)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.add_to_shopping_list))
            }

            RecipeInfoTabs(
                recipeInfo = recipeInfo,
                numberOfServings = numberOfServings
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeInformationSuccessScreenPreview() {
    RecipeInformationSuccessScreen(
        recipeInfo = Mocks.recipeInfoMock,
        addIngredients = { _, _ -> }
    )

}

@Preview(showBackground = true, locale = "ro")
@Composable
fun RecipeInformationSuccessScreenPreview_RO() {
    RecipeInformationSuccessScreen(
        recipeInfo = Mocks.recipeInfoMock,
        addIngredients = { _, _ -> }
    )
}

