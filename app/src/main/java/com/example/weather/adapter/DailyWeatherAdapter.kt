package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.RecyclerViewDailyWeatherBinding
import com.example.weather.model.ForecastdayView
import com.example.weather.viewholder.DailyWeatherViewHolder

class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherViewHolder>() {

    private val dailyForecast: MutableList<ForecastdayView> = ArrayList()

    fun setWeather(forecast: List<ForecastdayView>) {
        dailyForecast.clear()
        dailyForecast.addAll(forecast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RecyclerViewDailyWeatherBinding.inflate(view, parent, false)
        return DailyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(dailyForecast[position])
    }

    override fun getItemCount(): Int = dailyForecast.size

}