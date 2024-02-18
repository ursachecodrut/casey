package com.codrutursache.casey.data.remote.repository

import android.util.Log
import com.codrutursache.casey.data.remote.model.RecipeListResponse
import com.codrutursache.casey.data.remote.model.Response
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularService,
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