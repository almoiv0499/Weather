package com.example.weather.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.RecyclerViewHourlyWeatherBinding
import com.example.weather.model.HourView
import com.squareup.picasso.Picasso

class HourlyWeatherViewHolder(private val binding: RecyclerViewHourlyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hourlyForecast: HourView) {
        val context = this.itemView.context
        with(binding) {
            timeHourly.text = hourlyForecast.time
            temperatureHourly.text =
                context.getString(R.string.get_temperature, hourlyForecast.temp_c)
            Picasso.get().load(
                context.getString(
                    R.string.get_icon_weather,
                    hourlyForecast.conditionView.icon
                )
            ).into(iconWeatherHourly)
        }
    }

}