package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.data_source.SpoonacularService
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun IngredientListItem(
    ingredient: ExtendedIngredientResponse,
    numberOfServings: Int = 1,
) {
    ListItem(
        headlineContent = { Text(text = ingredient.name) },
        leadingContent = {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(64.dp)
                    .clip(RoundedCornerShape(10))
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            SpoonacularService.getFromCdn(
                                item = SpoonacularService.Companion.CdnItem.INGREDIENTS,
                                size = SpoonacularService.Companion.ImageSize.SMALL,
                                ingredient.image
                            )
                        )
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.recipe_placeholder),
                    contentDescription = ingredient.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        },
        supportingContent = {
            val amount = ingredient.measuresResponse.metricResponse.amount * numberOfServings
            val unit = ingredient.measuresResponse.metricResponse.unitShort

            Text(text = "${amount.formatAmountValue()} $unit")
        },
        tonalElevation = 10.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(10))
    )

}

fun Double.formatAmountValue(): String = if (this % 1.0 == 0.0) {
    this.toInt().toString()
} else {
    "%.2f".format(this)
}

@Preview(showBackground = true)
@Composable
fun IngredientCardPreview() {
    IngredientListItem(ingredient = Mocks.recipeInfoMock.extendedIngredientResponses.first())
}