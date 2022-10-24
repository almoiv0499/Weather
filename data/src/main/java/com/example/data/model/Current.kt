package com.example.data.model

data class Current(
    val condition: Condition,
    val humidity: Int,
    val temp_c: Double,
    val wind_kph: Double
)