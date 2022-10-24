package com.example.weather.model

data class CurrentView(
    val conditionView: ConditionView,
    val humidity: Int,
    val temp_c: Double,
    val wind_kph: Double,
)