package com.example.domain.model

data class ForecastdayDomain(
    val date: String,
    val day: DayDomain,
    val hour: List<HourDomain>
)