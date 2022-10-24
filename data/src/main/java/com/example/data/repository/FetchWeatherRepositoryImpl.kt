package com.example.data.repository

import com.example.data.extension.toWeatherDomain
import com.example.data.remote.Api
import com.example.domain.model.WeatherDomain
import com.example.domain.repository.FetchWeatherRepository

class FetchWeatherRepositoryImpl(private val api: Api) : FetchWeatherRepository {

    override suspend fun fetchWeather(city: String): WeatherDomain =
        api.fetchWeather(city).toWeatherDomain()

    override suspend fun fetchCurrentWeather(cityCoordinates: String): WeatherDomain =
        api.fetchWeather(cityCoordinates).toWeatherDomain()

}