package com.codrutursache.casey.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WinePairing(
    @Json(name = "pairedWines")
    val pairedWines: List<Any>,

    @Json(name = "pairingText")
    val pairingText: String,

    @Json(name = "productMatches")
    val productMatches: List<Any>
)