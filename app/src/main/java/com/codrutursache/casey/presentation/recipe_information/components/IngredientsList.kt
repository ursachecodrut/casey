package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun IngredientsList(
    ingredients: List<ExtendedIngredientResponse>,
    numberOfServings: Int = 1
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {


        ingredients.forEach { ingredient ->
            IngredientListItem(
                ingredient = ingredient,
                numberOfServings = numberOfServings
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun IngredientsListPreview() {
    IngredientsList(
        ingredients = listOf(
            Mocks.recipeInfoMock.extendedIngredientResponses.first()
        )
    )
}