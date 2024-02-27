package com.codrutursache.casey.data.remote.dto

import com.squareup.moshi.Json


data class RecipeDto(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "image")
    val image: String,

    @Json(name = "imageType")
    val imageType: String
)
