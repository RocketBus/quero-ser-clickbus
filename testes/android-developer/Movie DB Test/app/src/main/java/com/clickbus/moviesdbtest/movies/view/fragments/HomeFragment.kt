package com.clickbus.moviesdbtest.movies.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Dono do ViewModel
        val viewModel: BaseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.popularMovies()
        viewModel.genre()
        activity?.let { cxt ->

            viewModel.state.observe(cxt, Observer {

                Log.d("HomeFragment", "teste$it")
                handleState(it)

            })

        }

    }

    private fun handleState(state: State) {
        when (state) {
            is State.MovieList -> {
                // popular o recycler

            }
            is State.GenreList -> {
                //filtros de generes
            }
            is State.Failure -> {
                // messagem de erro 
            }
        }

    }
}


