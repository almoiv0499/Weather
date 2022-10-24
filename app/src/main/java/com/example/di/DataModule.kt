package com.example.di

import com.example.data.repository.FetchWeatherRepositoryImpl
import com.example.data.remote.RetrofitInstance
import com.example.domain.repository.FetchWeatherRepository
import org.koin.dsl.module

val dataModule = module {

    single<FetchWeatherRepository> {
        FetchWeatherRepositoryImpl(api = get())
    }

    single {
        RetrofitInstance.getApi()
    }

}