package com.heads.thinking.recativexlearn.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(var main: ForecastMain, var wind: Wind, @SerializedName("name") var cityName : String) {
}