package com.codrutursache.casey.data.remote.service

import com.codrutursache.casey.data.remote.dto.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularService {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com"
    }

    @GET("/recipes/complexSearch")
    suspend fun complexSearch(
        @Query("number") number: Int = 10,
        @Query("offset") offset: Int = 0,
    ): RecipeListDto
}