package com.heads.thinking.recativexlearn.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object {
        fun createApi(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}