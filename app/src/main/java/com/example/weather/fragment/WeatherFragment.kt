package com.example.weather.fragment

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.adapter.HourlyWeatherAdapter
import com.example.weather.adapter.DailyWeatherAdapter
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.model.WeatherView
import com.example.weather.util.TrackingUtility
import com.example.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WeatherFragment : BaseFragment<WeatherViewModel>(), View.OnKeyListener {

    private lateinit var binding: FragmentWeatherBinding

    private val locationManager by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val hourlyAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HourlyWeatherAdapter()
    }

    private val dailyAdapter by lazy(LazyThreadSafetyMode.NONE) {
        DailyWeatherAdapter()
    }

    override val viewModel by viewModel<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.inputCity.setOnKeyListener(this)

        viewModel.liveDataWeather.observe(requireActivity()) { weather ->
            setWeatherData(weather)
            dailyAdapter.setWeather(weather.forecast.forecastdayView)
            hourlyAdapter.setHour(weather.forecast.forecastdayView[0].hour)
        }

        viewModel.liveDataCurrentWeather.observe(requireActivity()) { weather ->
            binding.progressBar.visibility = View.GONE
            setWeatherData(weather)
            dailyAdapter.setWeather(weather.forecast.forecastdayView)
            hourlyAdapter.setHour(weather.forecast.forecastdayView[0].hour)
        }

        viewModel.liveDataCity.observe(requireActivity()) { city ->
            fetchWeather(city)
        }

        setCoordinates()
        setRecyclerViewHourly()
        setRecyclerViewDaily()

        return binding.root
    }

    private fun setRecyclerViewHourly() {
        with(binding.hourlyWeather) {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyAdapter
        }
    }

    private fun setRecyclerViewDaily() {
        with(binding.dailyWeather) {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = dailyAdapter
        }
    }

    private fun setCoordinates() {
        val receivedCoordinates = TrackingUtility.getCoordinate(
            locationManager = locationManager,
            context = requireActivity().applicationContext
        )
        val cityCoordinates = String.format(
            Locale.US,
            getString(R.string.get_coordinates),
            receivedCoordinates?.latitude,
            receivedCoordinates?.longitude
        )
        fetchCurrentWeather(cityCoordinates)
    }

    private fun fetchWeather(city: String) {
        viewModel.fetchWeather(city)
    }

    private fun fetchCurrentWeather(city: String) {
        viewModel.fetchCurrentWeather(city)
    }

    private fun setWeatherData(weather: WeatherView) {
        binding.temperature.text =
            getString(R.string.get_temperature, weather.current.temperature)
        binding.city.text = weather.location.cityName
        binding.weatherDescription.text = weather.current.description.description
        binding.humidity.text =
            getString(R.string.get_humidity_current, weather.current.humidity)
        binding.windSpeed.text =
            getString(R.string.get_wind_speed_current, weather.current.windSpeed)
        Picasso.get()
            .load(getString(R.string.get_icon_weather, weather.current.description.icon))
            .into(binding.iconWeather)
    }

    override fun onKey(view: View?, actionId: Int, event: KeyEvent?): Boolean {
        val city = binding.inputCity.text.toString().trim()
        viewModel.saveSateWeather(city)
        if (event?.action == KeyEvent.ACTION_DOWN && actionId == KeyEvent.KEYCODE_ENTER) {
            fetchWeather(city)
            return true
        }
        return false
    }

}