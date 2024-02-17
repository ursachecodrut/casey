package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.domain.model.RecipeListResponse
import com.codrutursache.casey.domain.model.Response

interface RecipesRepository {

    suspend fun getRecipes(): Response<RecipeListResponse>
}