package com.example.domain.repository

import com.example.domain.model.WeatherDomain

interface FetchWeatherRepository {

    suspend fun fetchWeather(city: String): WeatherDomain

    suspend fun fetchCurrentWeather(cityCoordinates: String): WeatherDomain

}