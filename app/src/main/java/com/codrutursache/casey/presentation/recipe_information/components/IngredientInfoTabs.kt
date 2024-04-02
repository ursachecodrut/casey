package com.codrutursache.casey.presentation.recipe_information.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun RecipeInfoTabs(
    recipeInfo: RecipeInformationResponse,
    numberOfServings: Int,
) {
    var selectedTab by remember { mutableStateOf(Tab.GENERAL) }
    val tabs: List<Tab> = listOf(Tab.GENERAL, Tab.INGREDIENTS, Tab.STEPS)

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
            IngredientsList(
                ingredients = recipeInfo.extendedIngredientResponses,
                numberOfServings = numberOfServings
            )
        }

        Tab.STEPS -> {
            recipeInfo.analyzedInstructionResponses.first().let {
                StepsList(steps = it.stepResponses)
            }
        }
    }
}

enum class Tab(@StringRes val title: Int) {
    GENERAL(R.string.general),
    INGREDIENTS(R.string.ingredients),
    STEPS(R.string.steps)
}

@Preview
@Composable
fun RecipeInfoTabsPreview() {
    RecipeInfoTabs(recipeInfo = Mocks.recipeInfoMock, numberOfServings = 1)

}