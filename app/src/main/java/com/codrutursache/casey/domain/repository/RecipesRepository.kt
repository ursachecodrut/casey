package com.codrutursache.casey.domain.repository

import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.data.response.RecipeListResponse
import com.codrutursache.casey.data.response.RecipeResponse
import com.codrutursache.casey.domain.model.Resource

interface RecipesRepository {

    suspend fun getRecipes(
        number: Int,
        offset: Int,
        recipeName: String = ""
    ): Resource<RecipeListResponse>

    suspend fun getRecipeInformation(id: Int): Resource<RecipeInformationResponse>

    suspend fun saveRecipe(recipeShort: RecipeResponse): Resource<Boolean>

    suspend fun unsaveRecipe(recipeId: Int): Resource<Boolean>
}