package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WinePairingResponse(
    @Json(name = "pairedWines")
    val pairedWines: List<Any>,

    @Json(name = "pairingText")
    val pairingText: String,

    @Json(name = "productMatches")
    val productMatches: List<Any>
)