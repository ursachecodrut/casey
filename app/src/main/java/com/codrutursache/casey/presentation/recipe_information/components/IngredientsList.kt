package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.ExtendedIngredientResponse
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun IngredientsList(
    ingredients: List<ExtendedIngredientResponse>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ingredients.forEach { ingredient ->
            IngredientListItem(ingredient = ingredient)
        }
    }

}