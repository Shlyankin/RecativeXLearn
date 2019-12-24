package com.heads.thinking.recativexlearn.mvp

import com.arellomobile.mvp.MvpView
import com.heads.thinking.recativexlearn.models.CurrentWeather

interface WeatherView : MvpView{

    fun showWeather(weather: CurrentWeather)

    fun showRequestError(t: Throwable)
}