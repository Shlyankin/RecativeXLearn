package com.heads.thinking.reactivexlearn.activities

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.heads.thinking.reactivexlearn.R
import com.heads.thinking.reactivexlearn.models.CurrentWeather
import com.heads.thinking.reactivexlearn.mvp.WeatherPresenter
import com.heads.thinking.reactivexlearn.mvp.WeatherView
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : MvpAppCompatActivity(), WeatherView {

    private val latitude = 53.1986
    private val longtitude = 50.114

    @InjectPresenter
    lateinit var weatherPresenter : WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        updateWeatherBtn.setOnClickListener {
            updateWeatherBtn.isClickable = false
            weatherCardView.visibility = View.GONE
            weatherProgressBar.visibility = View.VISIBLE
            weatherPresenter.updateWeather(latitude, longtitude)
        }
        weatherPresenter.updateWeather(latitude, longtitude)
    }

    override fun showWeather(weather: CurrentWeather) {
        cityTextView.text = getString(R.string.city) + " ${weather.cityName}"
        tempTextView.text = getString(R.string.temp) + " ${String.format("%.1f", weather.main.temp)} °C"
        pressureTextView.text = getString(R.string.pressure) + " ${String.format("%.1f", weather.main.pressure * 0.750063)} mmHg"
        weatherProgressBar.visibility = View.GONE
        weatherCardView.visibility = View.VISIBLE
        updateWeatherBtn.isClickable = true
    }

    override fun showRequestError(t: Throwable) {
        weatherProgressBar.visibility = View.GONE
        weatherCardView.visibility = View.VISIBLE
        updateWeatherBtn.isClickable = true
        cityTextView.text = "Error request. Try again later."
        tempTextView.text = t.localizedMessage
        pressureTextView.text = ""
    }
}
