package com.example.weather.fragment

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    companion object {
        private const val SAVED_STATE = "city name"
    }

    private lateinit var binding: FragmentWeatherBinding

    private val locationManager by lazy(LazyThreadSafetyMode.NONE) {
        requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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

        if (savedInstanceState != null) {
            val city = savedInstanceState.getString(SAVED_STATE, "")
            setCityName(city)
        }

        binding.inputCity.setOnKeyListener(this)

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
            context = requireContext()
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
        viewModel.liveDataWeather.observe(requireActivity()) { weather ->
            setWeatherData(weather)
            dailyAdapter.setWeather(weather.forecastView.forecastdayView)
            hourlyAdapter.setHour(weather.forecastView.forecastdayView[0].hourView)
        }
    }

    private fun fetchCurrentWeather(city: String) {
        viewModel.fetchCurrentWeather(city)
        viewModel.liveDataCurrentWeather.observe(requireActivity()) { weather ->
            binding.progressBar.visibility = View.GONE
            setWeatherData(weather)
            dailyAdapter.setWeather(weather.forecastView.forecastdayView)
            hourlyAdapter.setHour(weather.forecastView.forecastdayView[0].hourView)
        }
    }

    private fun setWeatherData(weather: WeatherView) {
        binding.temperature.text =
            getString(R.string.get_temperature, weather.currentView.temp_c)
        binding.city.text = weather.locationView.name
        binding.weatherDescription.text = weather.currentView.conditionView.text
        binding.humidity.text =
            getString(R.string.get_humidity_current, weather.currentView.humidity)
        binding.windSpeed.text =
            getString(R.string.get_wind_speed_current, weather.currentView.wind_kph)
        Picasso.get()
            .load(getString(R.string.get_icon_weather, weather.currentView.conditionView.icon))
            .into(binding.iconWeather)
    }

    override fun onKey(view: View?, actionId: Int, event: KeyEvent?): Boolean {
        val city = binding.inputCity.text.toString().trim()
        if (event?.action == KeyEvent.ACTION_DOWN && actionId == KeyEvent.KEYCODE_ENTER) {
            setCityName(city)
            return true
        }
        return false
    }

    private fun setCityName(city: String) {
        viewModel.setCityName(city)
        viewModel.liveDataCity.observe(requireActivity()) { cityName ->
            if (cityName.isNotEmpty()) {
                fetchWeather(cityName)
            } else
                Toast.makeText(
                    requireContext(),
                    getString(R.string.input_city_exception),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val city = binding.inputCity.text.toString().trim()
        outState.putString(SAVED_STATE, city)
    }

}