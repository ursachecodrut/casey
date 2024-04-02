package com.codrutursache.casey.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeasuresResponse(
    @Json(name = "metric")
    val metricResponse: MetricResponse,

    @Json(name = "us")
    val usResponse: UsResponse
)