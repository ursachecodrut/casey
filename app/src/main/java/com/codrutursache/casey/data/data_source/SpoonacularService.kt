package com.codrutursache.casey.data.data_source

import com.codrutursache.casey.data.response.RecipeInformationResponse
import com.codrutursache.casey.data.response.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularService {


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


    @GET("/recipes/informationBulk")
    suspend fun getRecipeInformationBulk(
        @Path("ids") ids: List<Int>,
        @Query("includeNutrition") includeNutrition: Boolean = false,
    ): List<RecipeInformationResponse>


    companion object {
        enum class ImageSize(val size: String) {
            SMALL("100x100"),
            MEDIUM("250x250"),
            LARGE("500x500"),
        }

        enum class CdnItem(val item: String) {
            INGREDIENTS("ingredients"),
            EQUIPMENT("equipment"),
        }

        const val BASE_URL = "https://api.spoonacular.com"
        const val RAPID_API_HOST = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
        const val RAPID_API_URL = "https://$RAPID_API_HOST"
        const val CDN_URL = "https://spoonacular.com/cdn"

        inline fun getFromCdn(item: CdnItem, size: ImageSize, name: String) =
            "$CDN_URL/${item.item}_${size.size}/$name"
    }

}