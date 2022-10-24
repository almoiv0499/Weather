package com.example.domain.model

data class WeatherDomain(
    val currentDomain: CurrentDomain,
    val forecastDomain: ForecastDomain,
    val locationDomain: LocationDomain
)