package com.example.domain.model

data class ForecastdayDomain(
    val date: String,
    val dayDomain: DayDomain,
    val hourDomain: List<HourDomain>
)