package com.example.domain.model

data class WeatherDomain(
    val current: CurrentDomain,
    val forecast: ForecastDomain,
    val location: LocationDomain
)