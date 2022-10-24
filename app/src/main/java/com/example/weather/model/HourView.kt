package com.example.weather.model

data class HourView(
    val conditionView: ConditionView,
    val temp_c: Double,
    val time: String
)