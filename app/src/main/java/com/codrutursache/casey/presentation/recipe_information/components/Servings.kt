package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun Servings(
    numberOfServings: Int,
    increment: () -> Unit,
    decrement: () -> Unit,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.servings),
            style = Typography.bodyLarge,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FilledTonalIconButton(
                onClick = decrement,
            ) {
                Text(
                    text = "-",
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                modifier = Modifier.width(32.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text( text = numberOfServings.toString(),
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }


            FilledTonalIconButton(
                onClick = increment,
            ) {
                Text(
                    text = "+",
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServingsPreview() {
    var servings by remember { mutableIntStateOf(1) }

    Servings(
        numberOfServings = servings,
        increment = { servings++ },
        decrement = {
            if (servings > 1) {
                servings--
            }
        }
    )
}