package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Score
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.presentation.components.LoadingScreen
import com.codrutursache.casey.presentation.theme.CaseyTheme
import com.codrutursache.casey.util.mock.Mocks
import kotlinx.coroutines.Job

@Composable
fun RecipeInformationScreen(
    resource: Resource<RecipeInformationResponse>,
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
            when (resource) {
                is Resource.Loading -> {
                    LoadingScreen()
                }

                is Resource.Success -> {
                    resource.data?.let {
                        RecipeInformationSuccessScreen(
                            recipeInfo = it,
                            addIngredients = addIngredients
                        )
                    }
                }

                is Resource.Failure -> {
                    Text(text = stringResource(R.string.something_went_wrong))
                }
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
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

    val recipeTags = mapOf<String, Boolean>(
        "vegetarian" to recipeInfo.vegetarian,
        "vegan" to recipeInfo.vegan,
        "gluten free" to recipeInfo.glutenFree,
        "dairy free" to recipeInfo.dairyFree,
        "very healthy" to recipeInfo.veryHealthy,
        "cheap" to recipeInfo.cheap,
        "very popular" to recipeInfo.veryPopular,
        "sustainable" to recipeInfo.sustainable,
        "low fodmap" to recipeInfo.lowFodmap,
    )

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

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                recipeTags.forEach { (tag, value) ->
                    if (value) {
                        Text(
                            text = tag,
                            style = Typography.bodyMedium,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.4f),
                                    shape = CircleShape
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Alarm,
                    contentDescription = stringResource(R.string.ready_in),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(
                        R.string.ready_in_minutes,
                        recipeInfo.readyInMinutes
                    ),
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Score,
                    contentDescription = stringResource(R.string.health_score),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(
                        R.string.health_score,
                        recipeInfo.healthScore
                    ),
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }


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

