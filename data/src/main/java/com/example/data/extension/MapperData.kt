package com.example.data.extension

import com.example.data.model.Weather
import com.example.domain.model.*

fun Weather.toWeatherDomain(): WeatherDomain = WeatherDomain(
    currentDomain = CurrentDomain(
        conditionDomain = ConditionDomain(
            text = current.condition.text,
            icon = current.condition.icon
        ),
        humidity = current.humidity,
        temp_c = current.temp_c,
        wind_kph = current.wind_kph
    ),
    forecastDomain = ForecastDomain(
        forecastdayDomain = forecast.forecastday.map { forecastday ->
            ForecastdayDomain(
                date = forecastday.date,
                dayDomain = DayDomain(
                    conditionDomain = ConditionDomain(
                        text = forecastday.day.condition.text,
                        icon = forecastday.day.condition.icon
                    ),
                    maxtemp_c = forecastday.day.maxtemp_c,
                    mintemp_c = forecastday.day.mintemp_c
                ),
                hourDomain = forecastday.hour.map { hour ->
                    HourDomain(
                        conditionDomain = ConditionDomain(
                            text = hour.condition.text,
                            icon = hour.condition.icon
                        ),
                        temp_c = hour.temp_c,
                        time = hour.time
                    )
                }
            )
        }
    ),
    locationDomain = LocationDomain(
        name = location.name,
        lat = location.lat,
        lon = location.lon
    )
)