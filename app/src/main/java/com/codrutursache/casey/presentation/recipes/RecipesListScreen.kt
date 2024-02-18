package com.codrutursache.casey.presentation.recipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.codrutursache.casey.data.remote.dto.RecipeListDto
import com.codrutursache.casey.util.Response


@Composable
fun RecipesListScreen(
    response: Response<RecipeListDto>
) {

    when (response) {
        is Response.Loading -> {
            Text(text = "Loading")
        }

        is Response.Success -> {
            response.data?.let {
                Text(text = it.totalResults.toString())
            }
        }

        is Response.Failure -> {
            Text(text = "${response.e}")
        }
    }
}