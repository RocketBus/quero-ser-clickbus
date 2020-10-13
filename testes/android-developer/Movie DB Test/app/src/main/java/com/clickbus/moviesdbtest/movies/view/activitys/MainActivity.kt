package com.clickbus.moviesdbtest.movies.view.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.view.OnClick
import com.clickbus.moviesdbtest.movies.view.adapters.HomeAdapter
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel:BaseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.popularMovies()

        viewModel.state.observe(this, Observer {
            Log.d("HomeActivity", "teste$it")
            handleState(it)

        })

        }
    private fun handleState(state: State) {
        when (state) {
            is State.MovieList -> {
                        rclHome.layoutManager = LinearLayoutManager(this@MainActivity,
                            LinearLayoutManager.VERTICAL,
                            false)
                        rclHome.adapter = HomeAdapter(state.listMovies).also{
                            it.onClickListener(this)
                        }
                }
            is State.GenreList -> {
                "Erro aqui"
            }
            is State.Failure -> {
                "Erro aqui"
            }
        }

    }
    override fun onCellClickListener() {
        startActivity(Intent(this@MainActivity, MovieDetailsActivity::class.java))
    }

}