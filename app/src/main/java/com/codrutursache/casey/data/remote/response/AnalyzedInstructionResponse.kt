package com.codrutursache.casey.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnalyzedInstructionResponse(
    @Json(name = "name")
    val name: String,

    @Json(name = "steps")
    val stepResponses: List<StepResponse>
)