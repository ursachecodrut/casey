package com.codrutursache.casey.presentation.recipe_information

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.presentation.base.ProgressBar
import com.codrutursache.casey.util.Response

@Composable
fun RecipeInformationScreen(
    recipeResponse: Response<RecipeInformationResponse>,
) {

    when (recipeResponse) {
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            recipeResponse.data?.let {
                Text(text = it.title)
            }
        }

        is Response.Failure -> {

            Text(text = recipeResponse.e.toString())
        }
    }

}