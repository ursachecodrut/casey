package com.codrutursache.casey.data.remote.dto

import com.squareup.moshi.Json


data class RecipeListDto(
    @Json(name = "offset")
    val offset: Int,

    @Json(name = "number")
    val number: Int,

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "results")
    val results: List<RecipeDto>
)
