package com.example.weather.extension

import com.example.domain.model.WeatherDomain
import com.example.weather.model.*

fun WeatherDomain.toWeatherView(): WeatherView = WeatherView(
    currentView = CurrentView(
        conditionView = ConditionView(
            text = currentDomain.conditionDomain.text,
            icon = currentDomain.conditionDomain.icon
        ),
        humidity = currentDomain.humidity,
        temp_c = currentDomain.temp_c,
        wind_kph = currentDomain.wind_kph
    ),
    forecastView = ForecastView(
        forecastdayView = forecastDomain.forecastdayDomain.map { forecastDayDomain ->
            ForecastdayView(
                date = forecastDayDomain.date,
                dayView = DayView(
                    conditionView = ConditionView(
                        text = forecastDayDomain.dayDomain.conditionDomain.text,
                        icon = forecastDayDomain.dayDomain.conditionDomain.icon
                    ),
                    maxtemp_c = forecastDayDomain.dayDomain.maxtemp_c,
                    mintemp_c = forecastDayDomain.dayDomain.mintemp_c
                ),
                hourView = forecastDayDomain.hourDomain.map { hourDomain ->
                    HourView(
                        conditionView = ConditionView(
                            text = hourDomain.conditionDomain.text,
                            icon = hourDomain.conditionDomain.icon
                        ),
                        temp_c = hourDomain.temp_c,
                        time = hourDomain.time
                    )
                }
            )
        }
    ),
    locationView = LocationView(
        name = locationDomain.name,
        lat = locationDomain.lat,
        lon = locationDomain.lon
    )
)