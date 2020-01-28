package com.heads.thinking.reactivexlearn.mvp

import com.arellomobile.mvp.MvpView
import com.heads.thinking.reactivexlearn.models.CurrentWeather

interface WeatherView : MvpView{

    fun showWeather(weather: CurrentWeather)

    fun showRequestError(t: Throwable)
}