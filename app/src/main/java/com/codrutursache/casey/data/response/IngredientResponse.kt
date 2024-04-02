package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "localizedName")
    val localizedName: String,

    @Json(name = "name")
    val name: String
)