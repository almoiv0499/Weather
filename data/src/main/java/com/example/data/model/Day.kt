package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("condition")
    val description: Condition,
    @SerializedName("maxtemp_c")
    val maxTemperature: Double,
    @SerializedName("mintemp_c")
    val minTemperature: Double,
)