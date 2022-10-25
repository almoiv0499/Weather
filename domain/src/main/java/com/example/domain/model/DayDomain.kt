package com.example.domain.model

data class DayDomain(
    val description: ConditionDomain,
    val maxTemperature: Double,
    val minTemperature: Double,
)