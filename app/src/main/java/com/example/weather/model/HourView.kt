package com.example.weather.model

data class HourView(
    val description: ConditionView,
    val temperature: Double,
    val time: String
)