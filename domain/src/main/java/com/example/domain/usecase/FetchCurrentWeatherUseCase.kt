package com.example.domain.usecase

import com.example.domain.repository.FetchWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FetchCurrentWeatherUseCase(
    private val repository: FetchWeatherRepository,
    private val ioDispatchers: CoroutineDispatcher
) {

    suspend fun fetchCurrentWeather(cityCoordinates: String) = withContext(ioDispatchers) {
        repository.fetchCurrentWeather(cityCoordinates)
    }

}