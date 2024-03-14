package com.codrutursache.casey.presentation.recipe_information

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.presentation.recipe_information.components.GeneralRecipeInfo
import com.codrutursache.casey.presentation.recipe_information.components.IngredientsList
import com.codrutursache.casey.presentation.recipe_information.components.StepsList
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.Response
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun RecipeInformationScreen(
    response: Response<RecipeInformationResponse>,
) {

    when (response) {
        is Response.Loading -> {
            Text(text = "Loading...")
        }

        is Response.Success -> {
            response.data?.let {
                RecipeInformationSuccessScreen(recipeInfo = it)
            }
        }

        is Response.Failure -> {
            Text(text = stringResource(R.string.something_went_wrong))
        }
    }
}

@Composable
fun RecipeInformationSuccessScreen(
    recipeInfo: RecipeInformationResponse,
) {
    val scrollState = rememberScrollState()

    var selectedTab by remember { mutableStateOf(Tab.GENERAL) }
    val tabs = listOf(Tab.GENERAL, Tab.INGREDIENTS, Tab.STEPS)

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



            TabRow(
                selectedTabIndex = tabs.indexOf(selectedTab),
                modifier = Modifier
                    .height(48.dp),
            ) {
                tabs.forEach { tab ->
                    val possibleCount = when (tab) {
                        Tab.GENERAL -> null
                        Tab.INGREDIENTS -> recipeInfo.extendedIngredientResponses.size
                        Tab.STEPS -> recipeInfo.analyzedInstructionResponses.first().stepResponses.size
                    }

                    Tab(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        modifier = Modifier
                            .height(48.dp)
                    ) {
                        Text(
                            text = "${stringResource(tab.title)}${possibleCount?.let { " ($it)" } ?: ""}",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = Typography.bodyLarge,
                        )
                    }

                }
            }

            when (selectedTab) {
                Tab.GENERAL -> {
                    GeneralRecipeInfo(summary = recipeInfo.summary)
                }

                Tab.INGREDIENTS -> {
                    IngredientsList(ingredients = recipeInfo.extendedIngredientResponses)
                }

                Tab.STEPS -> {
                    recipeInfo.analyzedInstructionResponses.first().let {
                        StepsList(steps = it.stepResponses)
                    }
                }
            }
        }
    }
}


enum class Tab(@StringRes val title: Int) {
    GENERAL(R.string.general),
    INGREDIENTS(R.string.ingredients),
    STEPS(R.string.steps)
}


@Preview(showBackground = true)
@Composable
fun RecipeInformationSuccessScreenPreview() {
    RecipeInformationSuccessScreen(recipeInfo = Mocks.recipeInfoMock)

}

@Preview(showBackground = true, locale = "ro")
@Composable
fun RecipeInformationSuccessScreenPreview_RO() {
    RecipeInformationSuccessScreen(recipeInfo = Mocks.recipeInfoMock)
}

