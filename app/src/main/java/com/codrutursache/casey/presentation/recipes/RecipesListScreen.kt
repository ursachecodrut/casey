package com.codrutursache.casey.presentation.recipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.codrutursache.casey.data.remote.model.RecipeListResponse
import com.codrutursache.casey.data.remote.model.Response


@Composable
fun RecipesListScreen(
    response: Response<RecipeListResponse>
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