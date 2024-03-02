package com.codrutursache.casey.presentation.recipe_information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codrutursache.casey.R
import com.codrutursache.casey.data.remote.response.StepResponse
import com.codrutursache.casey.presentation.theme.Typography

@Composable
fun StepsList(
    steps: List<StepResponse>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        steps.forEach { step ->
            StepListItem(step = step)
        }
    }
}