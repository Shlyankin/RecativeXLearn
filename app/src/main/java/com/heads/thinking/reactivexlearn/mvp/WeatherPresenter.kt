package com.heads.thinking.reactivexlearn.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.heads.thinking.reactivexlearn.api.WeatherApi
import com.heads.thinking.reactivexlearn.models.CurrentWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class WeatherPresenter : MvpPresenter<WeatherView>() {

    private var disposable : Disposable? = null

    fun updateWeather(latitude: Double, longtitude: Double) {
        WeatherApi.resetWeatherObservable(latitude, longtitude)
        disposable = WeatherApi.getWeather(latitude, longtitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({weather : CurrentWeather ->
                viewState.showWeather(weather)
            }, {
                viewState.showRequestError(it)
            })
    }

    fun unsubscribe() {
        if(disposable?.isDisposed == false) disposable?.dispose()
    }
}