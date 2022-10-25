package com.example.weather.extension

import com.example.domain.model.WeatherDomain
import com.example.weather.model.*

fun WeatherDomain.toWeatherView(): WeatherView = WeatherView(
    current = CurrentView(
        description = ConditionView(
            description = current.description.description,
            icon = current.description.icon
        ),
        humidity = current.humidity,
        temperature = current.temperature,
        windSpeed = current.windSpeed
    ),
    forecast = ForecastView(
        forecastdayView = forecast.forecastdayDomain.map { forecastDayDomain ->
            ForecastdayView(
                date = forecastDayDomain.date,
                day = DayView(
                    description = ConditionView(
                        description = forecastDayDomain.day.description.description,
                        icon = forecastDayDomain.day.description.icon
                    ),
                    maxTemperature = forecastDayDomain.day.maxTemperature,
                    minTemperature = forecastDayDomain.day.minTemperature
                ),
                hour = forecastDayDomain.hour.map { hourDomain ->
                    HourView(
                        description = ConditionView(
                            description = hourDomain.description.description,
                            icon = hourDomain.description.icon
                        ),
                        temperature = hourDomain.temperature,
                        time = hourDomain.time
                    )
                }
            )
        }
    ),
    location = LocationView(
        cityName = location.cityName,
        latitude = location.latitude,
        longitude = location.longitude
    )
)