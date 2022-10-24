package com.example.di

import com.example.domain.usecase.FetchCurrentWeatherUseCase
import com.example.domain.usecase.FetchWeatherUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {

    factory {
        FetchWeatherUseCase(
            repository = get(),
            ioDispatchers = get()
        )
    }

    factory {
        FetchCurrentWeatherUseCase(
            repository = get(),
            ioDispatchers = get()
        )
    }

    single {
        Dispatchers.IO
    }

}