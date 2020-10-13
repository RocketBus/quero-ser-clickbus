package com.clickbus.moviesdbtest.movies.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_filter_movies.*


class FilterMoviesFragment : Fragment(R.layout.fragment_filter_movies) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel:BaseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.genre()
        activity?.let {fragment ->
            viewModel.state.observe(fragment, Observer {
                Log.d("Filmes", "teste$it")
            })
        }



    }

    fun spnGenre(){
        val genreFilter = spnGenre

    }

}