package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StepResponse(
    @Json(name = "equipment")
    val equipmentResponses: List<EquipmentResponse>,

    @Json(name = "ingredients")
    val ingredientResponses: List<IngredientResponse>,

    @Json(name = "length")
    val lengthResponse: LengthResponse?,

    @Json(name = "number")
    val number: Int,

    @Json(name = "step")
    val step: String
)