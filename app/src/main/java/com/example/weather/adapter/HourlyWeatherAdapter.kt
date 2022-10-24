package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.RecyclerViewHourlyWeatherBinding
import com.example.weather.model.HourView
import com.example.weather.viewholder.HourlyWeatherViewHolder

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherViewHolder>() {

    private val hourlyForecast: MutableList<HourView> = ArrayList()

    fun setHour(forecast: List<HourView>) {
        hourlyForecast.clear()
        hourlyForecast.addAll(forecast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RecyclerViewHourlyWeatherBinding.inflate(view, parent, false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(hourlyForecast = hourlyForecast[position])
    }

    override fun getItemCount(): Int = hourlyForecast.size

}