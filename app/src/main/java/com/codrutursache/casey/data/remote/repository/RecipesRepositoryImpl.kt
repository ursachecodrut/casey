package com.codrutursache.casey.data.remote.repository

import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.domain.repository.RecipesRepository
import com.codrutursache.casey.util.Response
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularService,
) : RecipesRepository {
    override suspend fun getRecipes(number: Int, offset: Int): Response<RecipeListResponse> =
        try {
            val response = api.complexSearch(number = number, offset = offset)
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }

    override suspend fun getRecipeInformation(id: Int): Response<RecipeInformationResponse> =
        try {
            val response = api.getRecipeInformation(id)
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }

}