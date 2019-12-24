package com.heads.thinking.recativexlearn.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.heads.thinking.recativexlearn.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View) {
        when(view.id){
            first.id -> {
                startActivity(Intent(this, RxJavaSimpleActivity::class.java))
            }
            second.id -> {
                startActivity(Intent(this, ColorsActivity::class.java))
            }
            third.id -> {
                startActivity(Intent(this, BooksActivity::class.java))
            }
            fourth.id -> {
                startActivity(Intent(this, WeatherActivity::class.java))
            }
        }
    }
}
