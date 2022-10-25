package com.example.domain.model

data class HourDomain(
    val description: ConditionDomain,
    val temperature: Double,
    val time: String
)