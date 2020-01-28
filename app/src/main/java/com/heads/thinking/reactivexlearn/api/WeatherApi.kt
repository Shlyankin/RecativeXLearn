package com.heads.thinking.reactivexlearn.api

import com.heads.thinking.reactivexlearn.models.CurrentWeather
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class WeatherApi {
    companion object {
        private var api: Api = ApiFactory.createApi()
        private var observableApi: Observable<CurrentWeather>? = null
        private var subscriptionApi: Disposable? = null
        private var weatherSubject: BehaviorSubject<CurrentWeather>? = null

        fun getWeather(latitude: Double, longtitude: Double): Observable<CurrentWeather> {
            if (weatherSubject == null) resetWeatherObservable(latitude, longtitude)
            return weatherSubject!!
        }

        fun resetWeatherObservable(latitude: Double, longtitude: Double) {
            weatherSubject = BehaviorSubject.create()
            if (subscriptionApi?.isDisposed == true) subscriptionApi?.dispose()
            observableApi =
                api.getCurrentWeather(latitude, longtitude, Constants.API_KEY, "metric")
                subscriptionApi = observableApi
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    // onNext
                    weatherSubject?.onNext(it)
                }, {
                    //onError
                    weatherSubject?.onError(it)
                }, {
                    weatherSubject?.onComplete()
                })
        }

    }
}