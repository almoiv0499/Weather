package com.example.weather.model

data class DayView(
    val description: ConditionView,
    val maxTemperature: Double,
    val minTemperature: Double,
)