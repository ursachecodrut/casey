package com.codrutursache.casey.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaloricBreakdown(
    @Json(name = "percentCarbs")
    val percentCarbs: Double,

    @Json(name = "percentFat")
    val percentFat: Double,

    @Json(name = "percentProtein")
    val percentProtein: Double
)