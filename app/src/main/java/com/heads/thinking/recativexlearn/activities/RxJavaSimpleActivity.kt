package com.heads.thinking.recativexlearn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import android.os.SystemClock
import android.widget.Toast
import com.heads.thinking.recativexlearn.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java_simple.*


class RxJavaSimpleActivity : AppCompatActivity() {

    var disposable = CompositeDisposable()
    var value = 0
    private val serverDownloadObservable : Observable<Int> = Observable.create { emitter ->
        SystemClock.sleep(10000) // simulate delay
        emitter.onNext(5)
        emitter.onComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_simple)
        serverButton.setOnClickListener {btn->
            btn.isEnabled = false
            val subscribe : Disposable = serverDownloadObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { value ->
                    updateTheUserInterface(value)
                    btn.isEnabled = true
                }
            disposable.add(subscribe)
        }
        toastButton.setOnClickListener {
            Toast.makeText(this, "Still active ${value++}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTheUserInterface(value: Int) {
        resultTextView.text = "$value"
    }

    override fun onStop() {
        super.onStop()
        if(!disposable.isDisposed) disposable.dispose()
    }
}
