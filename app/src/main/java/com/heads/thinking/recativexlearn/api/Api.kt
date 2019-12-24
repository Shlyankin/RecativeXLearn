package com.heads.thinking.recativexlearn.api

import com.heads.thinking.recativexlearn.models.CurrentWeather
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    fun getCurrentWeather(@Query("lat") latitude: Double,
                          @Query("lon") longtitude: Double,
                          @Query("appid") apiKey: String,
                          @Query("units") units: String) : Observable<CurrentWeather>
}