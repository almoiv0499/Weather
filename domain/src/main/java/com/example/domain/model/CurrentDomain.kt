package com.example.domain.model

data class CurrentDomain(
    val conditionDomain: ConditionDomain,
    val humidity: Int,
    val temp_c: Double,
    val wind_kph: Double,
)