package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.data.response.StepResponse
import com.codrutursache.casey.presentation.theme.Typography
import com.codrutursache.casey.util.mock.Mocks

@Composable
fun StepListItem(
    step: StepResponse
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "${step.number}",
            style = Typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = step.step,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun StepListItemPreview() {
    StepListItem(step = Mocks.recipeInfoMock.analyzedInstructionResponses.first().stepResponses.first())

}

