package com.heads.thinking.reactivexlearn.models

import com.google.gson.annotations.SerializedName

data class ForecastMain(var temp: Double, var pressure: Double, var humidity: Double,
                        @SerializedName("temp_min") var minTemp: Double,
                        @SerializedName("temp_max") var maxTemp: Double) {
}