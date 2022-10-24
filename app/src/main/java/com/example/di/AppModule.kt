package com.example.di

import com.example.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        WeatherViewModel(
            fetchWeatherUseCase = get(),
            fetchCurrentWeatherUseCase = get()
        )
    }

}