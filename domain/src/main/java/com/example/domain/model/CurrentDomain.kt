package com.example.domain.model

data class CurrentDomain(
    val description: ConditionDomain,
    val humidity: Int,
    val temperature: Double,
    val windSpeed: Double,
)