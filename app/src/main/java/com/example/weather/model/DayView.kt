package com.example.weather.model

data class DayView(
    val conditionView: ConditionView,
    val maxtemp_c: Double,
    val mintemp_c: Double,
)