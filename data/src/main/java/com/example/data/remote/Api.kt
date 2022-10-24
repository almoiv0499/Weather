package com.example.data.remote

import com.example.data.model.Weather
import com.example.data.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/forecast.json?key=${Constants.API_KEY}")
    suspend fun fetchWeather(
        @Query("q") city: String,
        @Query("days") days: Int = 7,
        @Query("lang") lang: String = "ru"
    ): Weather

}