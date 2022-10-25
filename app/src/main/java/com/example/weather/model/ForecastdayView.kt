package com.example.weather.model

data class ForecastdayView(
    val date: String,
    val day: DayView,
    val hour: List<HourView>
)