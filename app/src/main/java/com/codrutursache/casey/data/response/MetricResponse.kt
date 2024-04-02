package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetricResponse(
    @Json(name = "amount")
    val amount: Double,

    @Json(name = "unitLong")
    val unitLong: String,

    @Json(name = "unitShort")
    val unitShort: String
)