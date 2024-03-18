package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LengthResponse(
    @Json(name = "number")
    val number: Int,

    @Json(name = "unit")
    val unit: String
)