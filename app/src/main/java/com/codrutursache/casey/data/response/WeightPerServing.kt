package com.codrutursache.casey.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeightPerServing(
    @Json(name = "amount")
    val amount: Int,

    @Json(name = "unit")
    val unit: String
)