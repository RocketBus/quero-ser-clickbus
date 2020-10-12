package com.clickbus.moviesdbtest.movies.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.view.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HomeFragment()
        
    }
}