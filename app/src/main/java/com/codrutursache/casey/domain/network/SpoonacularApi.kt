package com.codrutursache.casey.domain.network

import com.codrutursache.casey.BuildConfig
import com.codrutursache.casey.domain.model.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com"
    }

    @GET("/recipes/complexSearch")
    suspend fun complexSearch(
        @Query("number") number: Int = 10,
        @Query("offset") offset: Int = 0,
    ): RecipeListResponse
}