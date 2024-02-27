package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse
import com.codrutursache.casey.util.Response

interface RecipesRepository {

    suspend fun getRecipes(number: Int, offset: Int): Response<RecipeListResponse>

    suspend fun getRecipeInformation(id: Int): Response<RecipeInformationResponse>
}