package com.codrutursache.casey.data.repository

import android.util.Log
import com.codrutursache.casey.domain.model.RecipeListResponse
import com.codrutursache.casey.domain.model.Response
import com.codrutursache.casey.domain.network.SpoonacularApi
import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularApi,
) : RecipesRepository {
    override suspend fun getRecipes(): Response<RecipeListResponse> {
        return try {
            val response = api.complexSearch()

            Response.Success(response)
        } catch (e: Exception) {
            Log.d("RecipesRepositoryImpl", "getRecipes: ${e.message}")
            Response.Failure(e)
        }
    }

}