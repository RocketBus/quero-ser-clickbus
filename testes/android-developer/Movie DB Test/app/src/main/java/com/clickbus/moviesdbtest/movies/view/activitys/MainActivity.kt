package com.clickbus.moviesdbtest.movies.view.activitys


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import android.os.Bundle
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.view.OnClick
import com.clickbus.moviesdbtest.movies.view.adapters.HomeAdapter
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.clickbus.moviesdbtest.R

class MainActivity : AppCompatActivity(R.layout.activity_main), OnClick {


    private lateinit var viewModel: BaseViewModel
    private val adapter = HomeAdapter()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,BaseViewModel.BaseViewModelFactory()).get(BaseViewModel::class.java)

        rclHome.adapter = adapter.also {
            it.onClickListener(this)
        }

        viewModel.movies.observe(this, Observer { handleState(it) })

        viewModel.upMovies()
    }

    private fun handleState(message: State.Message<List<Movie>>) {
        when (message.status) {
            State.Status.START -> {
                view_flipper.displayedChild = 0
            }

            State.Status.COMPLETE -> {
                view_flipper.displayedChild = 1

                adapter.movies.clear()
                adapter.movies.addAll(message.data!!)
                adapter.notifyDataSetChanged()
            }

            State.Status.ERROR -> {
                view_flipper.displayedChild = 2
            }

        }

    }

    override fun onCellClickListener(movie: Movie) {
        startActivity(MovieDetailsActivity.callMovieDetails(this@MainActivity, movie))
    }


}