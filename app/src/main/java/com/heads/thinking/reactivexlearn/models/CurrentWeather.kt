package com.heads.thinking.reactivexlearn.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(var main: ForecastMain, var wind: Wind, @SerializedName("name") var cityName : String) {
}