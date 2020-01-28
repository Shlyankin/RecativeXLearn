package com.heads.thinking.reactivexlearn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.heads.thinking.reactivexlearn.R
import com.heads.thinking.reactivexlearn.adapters.SimpleStringAdapter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {

    private lateinit var simpleStringAdapter: SimpleStringAdapter
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_colors)
        colorListView.layoutManager = LinearLayoutManager(this)
        simpleStringAdapter = SimpleStringAdapter(this)
        colorListView.adapter = simpleStringAdapter
    }

    private fun createObservable() {
        val listObservable: Observable<List<String>> = Observable.just(getColorList())
        disposable = listObservable.subscribe {colors ->
            simpleStringAdapter.mStrings = colors
        }
    }

    private fun getColorList(): List<String> = arrayListOf("red", "green", "blue", "pink", "brown")

    override fun onStop() {
        super.onStop()
        if(!disposable.isDisposed) disposable.dispose()
    }
}
