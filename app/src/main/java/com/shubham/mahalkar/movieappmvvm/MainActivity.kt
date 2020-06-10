package com.shubham.mahalkar.movieappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.mahalkar.movieappmvvm.movie_details.ActivitySingleMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener{
            val intent = Intent(this, ActivitySingleMovie::class.java)
            intent.putExtra("id", 454626)
            this.startActivity(intent)
        }
    }
}