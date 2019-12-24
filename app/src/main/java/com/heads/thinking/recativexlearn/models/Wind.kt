package com.heads.thinking.recativexlearn.models

import com.google.gson.annotations.SerializedName

data class Wind(var speed: Double, @SerializedName("deg") var degree : Double) {
}