package com.clickbus.moviesdbtest.movies.view.activitys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Genre
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.models.MovieDetail
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            BaseViewModel.BaseViewModelFactory()
        ).get(BaseViewModel::class.java)

        val movie = intent.extras?.getParcelable<Movie>("movie")!!

        viewModel.movie.observe(this, Observer { handleState(it) })
        viewModel.uphMovieDetails(movie.id)
    }

    private fun handleState(message: State.Message<MovieDetail>) {
        when (message.status) {
            State.Status.START -> {
                view_flipper_movie_details.displayedChild = 0
            }

            State.Status.COMPLETE -> {
                view_flipper_movie_details.displayedChild = 1
                interfaceUI(message)

            }

            State.Status.ERROR -> {
                view_flipper_movie_details.displayedChild = 2
            }

        }

    }

    companion object {
        fun callMovieDetails(parent: Context, movie: Movie): Intent {
            return Intent(parent, MovieDetailsActivity::class.java).also {
                it.putExtra("movie", movie)
            }
        }
    }



    fun interfaceUI(details: State.Message<MovieDetail>){
        txvTitle.text = details.data?.title
        txvOriginalTitle.text = details.data?.originalTitle
        txvdateMovie.text = details.data?.releaseDate
        txvOverview.text = details.data?.overview
        txvGenre.text = details.data?.genreList?.get(1)?.name.toString()

        val baseUrl = "http://image.tmdb.org/t/p/original/"

        val background = details.data?.backdropPath
        val poster = "$baseUrl$background"
        Glide.with(this)
            .load(poster)
            .centerCrop()
            .into(imgBackground)

        val image = details.data?.posterPath
        val imageMovie = "$baseUrl$image"
        Glide.with(this)
            .load(imageMovie)
            .centerCrop()
            .into(imgMovie)


    }





}
