package com.heads.thinking.reactivexlearn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.heads.thinking.reactivexlearn.R
import com.heads.thinking.reactivexlearn.test.RestClient
import com.heads.thinking.reactivexlearn.adapters.SimpleStringAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {

    private lateinit var bookSubscription: Disposable
    private lateinit var simpleStringAdapter: SimpleStringAdapter
    private lateinit var restClient : RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restClient = RestClient(this)
        configureLayout()
        createObservable()
    }

    private fun createObservable() {
        val booksObservable : Observable<List<String>> = Observable.fromCallable { restClient.getFavoriteBooks()}
        bookSubscription = booksObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {strings ->
                displayBooks(strings)
            }
    }

    private fun displayBooks(strings: List<String>) {
        simpleStringAdapter.mStrings = strings
        progressBar.visibility = View.GONE
        booksListView.visibility = View.VISIBLE
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_books)
        booksListView.layoutManager = LinearLayoutManager(this)
        simpleStringAdapter = SimpleStringAdapter(this)
        booksListView.adapter = simpleStringAdapter
    }

    override fun onStop() {
        super.onStop()
        if(!bookSubscription.isDisposed) bookSubscription.dispose()
    }
}
