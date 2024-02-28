package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun IngredientCard(
    ingredient: ExtendedIngredientResponse
) {
    Card {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 2.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
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

                Text(
                    text = ingredient.name,
                    style = Typography.bodySmall,
                )
            }


            val amount = ingredient.measuresResponse.metricResponse.amount.toInt()
            val unit = ingredient.measuresResponse.metricResponse.unitShort

            Text(
                text = "$amount $unit",
                style = Typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
