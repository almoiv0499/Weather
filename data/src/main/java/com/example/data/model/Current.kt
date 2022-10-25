package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("condition")
    val description: Condition,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temp_c")
    val temperature: Double,
    @SerializedName("wind_kph")
    val windSpeed: Double
)