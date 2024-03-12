package com.codrutursache.casey.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RecipeResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "image")
    val image: String,

    @Json(name = "imageType")
    val imageType: String
) {
    constructor() : this(0, "", "", "")
}
