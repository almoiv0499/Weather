package com.example.domain.usecase

import com.example.domain.repository.FetchWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FetchWeatherUseCase(
    private val repository: FetchWeatherRepository,
    private val ioDispatchers: CoroutineDispatcher
) {

    suspend fun fetchWeather(city: String) = withContext(ioDispatchers) {
        repository.fetchWeather(city)
    }

}