package com.example.weather.model

data class CurrentView(
    val description: ConditionView,
    val humidity: Int,
    val temperature: Double,
    val windSpeed: Double,
)