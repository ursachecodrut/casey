package com.codrutursache.casey.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RandomFoodTriviaResponse(

    @Json(name = "text")
    val text: String
)