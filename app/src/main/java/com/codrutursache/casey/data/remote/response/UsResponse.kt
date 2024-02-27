package com.codrutursache.casey.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsResponse(
    @Json(name = "amount")
    val amount: Double,

    @Json(name = "unitLong")
    val unitLong: String,

    @Json(name = "unitShort")
    val unitShort: String
)