package com.example.weather.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.RecyclerViewDailyWeatherBinding
import com.example.weather.model.ForecastdayView
import com.squareup.picasso.Picasso

class DailyWeatherViewHolder(private val binding: RecyclerViewDailyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dailyForecast: ForecastdayView) {
        val context = this.itemView.context
        with(binding) {
            temperatureDaily.text = context.getString(
                R.string.get_max_min_temperature_daily,
                dailyForecast.day.maxTemperature,
                dailyForecast.day.minTemperature
            )
            dayDaily.text = dailyForecast.date
            weatherDescriptionDaily.text = dailyForecast.day.description.description
            Picasso.get().load(
                context.getString(
                    R.string.get_icon_weather,
                    dailyForecast.day.description.icon
                )
            ).into(iconWeatherDaily)
        }
    }


}