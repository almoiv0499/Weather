package com.example.weather.model

import java.io.Serializable

data class WeatherView(
    val current: CurrentView,
    val forecast: ForecastView,
    val location: LocationView
) : Serializable