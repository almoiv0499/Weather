package com.example.data.model

data class Forecastday(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)