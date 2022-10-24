package com.example.weather.model

import java.io.Serializable

data class WeatherView(
    val currentView: CurrentView,
    val forecastView: ForecastView,
    val locationView: LocationView
) : Serializable