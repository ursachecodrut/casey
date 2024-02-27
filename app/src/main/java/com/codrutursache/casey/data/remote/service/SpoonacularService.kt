package com.codrutursache.casey.data.remote.service

import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularService {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com"
    }

    @GET("/recipes/complexSearch")
    suspend fun complexSearch(
        @Query("number") number: Int = 10,
        @Query("offset") offset: Int = 0,
    ): RecipeListResponse

    @GET("/recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
    ): RecipeInformationResponse
}