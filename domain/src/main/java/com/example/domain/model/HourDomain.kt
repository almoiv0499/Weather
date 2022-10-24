package com.example.domain.model

data class HourDomain(
    val conditionDomain: ConditionDomain,
    val temp_c: Double,
    val time: String
)