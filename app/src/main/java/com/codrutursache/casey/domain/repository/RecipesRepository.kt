package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.remote.dto.RecipeListDto
import com.codrutursache.casey.util.Response

interface RecipesRepository {

    suspend fun getRecipes(): Response<RecipeListDto>
}