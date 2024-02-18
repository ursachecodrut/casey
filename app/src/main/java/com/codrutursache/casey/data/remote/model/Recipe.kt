package com.codrutursache.casey.data.remote.model

import com.squareup.moshi.Json

data class RecipeListResponse(
    @Json(name = "offset")
    val offset: Int,

    @Json(name = "number")
    val number: Int,

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "results")
    val results: List<Recipe>
)

data class Recipe(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "image")
    val image: String,

    @Json(name = "imageType")
    val imageType: String
)
