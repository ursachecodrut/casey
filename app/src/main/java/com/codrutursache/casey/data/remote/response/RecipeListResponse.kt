package com.codrutursache.casey.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RecipeListResponse(
    @Json(name = "offset")
    val offset: Int,

    @Json(name = "number")
    val number: Int,

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "results")
    val results: List<RecipeResponse>
)
