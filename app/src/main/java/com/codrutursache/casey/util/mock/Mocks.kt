package com.codrutursache.casey.util.mock

import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse

object Mocks {

    // write me a recipeDto with some actual real values
    private val recipeResponse = RecipeResponse(
        id = 1,
        title = "Chicken Pasta",
        image = "https://spoonacular.com/recipeImages/715497-312x231.jpg",
        imageType = "jpg"
    )

    // create a list of recipeDtos using a range
    val recipeListDto = RecipeListResponse(
        offset = 0,
        number = 10,
        totalResults = 100,
        results = (1..100).map { recipeResponse }
    )
}