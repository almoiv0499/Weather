package com.example.data.extension

import com.example.data.model.Weather
import com.example.domain.model.*

fun Weather.toWeatherDomain(): WeatherDomain = WeatherDomain(
    current = CurrentDomain(
        description = ConditionDomain(
            description = current.description.description,
            icon = current.description.icon
        ),
        humidity = current.humidity,
        temperature = current.temperature,
        windSpeed = current.windSpeed
    ),
    forecast = ForecastDomain(
        forecastdayDomain = forecast.forecastday.map { forecastday ->
            ForecastdayDomain(
                date = forecastday.date,
                day = DayDomain(
                    description = ConditionDomain(
                        description = forecastday.day.description.description,
                        icon = forecastday.day.description.icon
                    ),
                    maxTemperature = forecastday.day.maxTemperature,
                    minTemperature = forecastday.day.minTemperature
                ),
                hour = forecastday.hour.map { hour ->
                    HourDomain(
                        description = ConditionDomain(
                            description = hour.description.description,
                            icon = hour.description.icon
                        ),
                        temperature = hour.temperature,
                        time = hour.time
                    )
                }
            )
        }
    ),
    location = LocationDomain(
        cityName = location.cityName,
        latitude = location.latitude,
        longitude = location.longitude
    )
)