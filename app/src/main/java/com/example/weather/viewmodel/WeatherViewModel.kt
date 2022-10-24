package com.example.weather.viewmodel

import androidx.lifecycle.*
import com.example.domain.usecase.FetchCurrentWeatherUseCase
import com.example.domain.usecase.FetchWeatherUseCase
import com.example.weather.R
import com.example.weather.extension.toWeatherView
import com.example.weather.model.WeatherView
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherViewModel(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase
) : BaseViewModel() {

    private val _liveDataWeather = MutableLiveData<WeatherView>()
    val liveDataWeather: LiveData<WeatherView> = _liveDataWeather

    private val _liveDataCurrentWeather = MutableLiveData<WeatherView>()
    val liveDataCurrentWeather: LiveData<WeatherView> = _liveDataCurrentWeather

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                _liveDataWeather.value =
                    fetchWeatherUseCase.fetchWeather(city).toWeatherView()
            } catch (exception: HttpException) {
                message(R.string.exception_message)
            }
        }
    }

    fun fetchCurrentWeather(cityCoordinates: String) {
        viewModelScope.launch {
            try {
                _liveDataCurrentWeather.value =
                    fetchCurrentWeatherUseCase.fetchCurrentWeather(cityCoordinates).toWeatherView()
            } catch (exception: HttpException) {
                message(R.string.exception_message_current)
            }
        }
    }

    private fun message(res: Int) = setMessage(res)

}