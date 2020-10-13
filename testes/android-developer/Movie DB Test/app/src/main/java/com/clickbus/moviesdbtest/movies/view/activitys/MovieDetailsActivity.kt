package com.clickbus.moviesdbtest.movies.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val viewModel: BaseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)

        viewModel.MovieDetail()

    }
}