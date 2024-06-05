package com.codrutursache.casey.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    @Json(name = "amount")
    val amount: Double,

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "nutrients")
    val nutrients: List<NutrientX>,

    @Json(name = "unit")
    val unit: String
)