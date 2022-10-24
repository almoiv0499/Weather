package com.example.weather.model

data class ForecastdayView(
    val date: String,
    val dayView: DayView,
    val hourView: List<HourView>
)