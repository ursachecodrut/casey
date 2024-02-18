package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.remote.model.RecipeListResponse
import com.codrutursache.casey.data.remote.model.Response

interface RecipesRepository {

    suspend fun getRecipes(): Response<RecipeListResponse>
}