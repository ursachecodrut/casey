package com.codrutursache.casey.data.remote.repository

import com.codrutursache.casey.data.remote.dto.RecipeListDto
import com.codrutursache.casey.data.remote.service.SpoonacularService
import com.codrutursache.casey.domain.repository.RecipesRepository
import com.codrutursache.casey.util.Response
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularService,
) : RecipesRepository {
    override suspend fun getRecipes(): Response<RecipeListDto> {
        return try {
            val response = api.complexSearch()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

}