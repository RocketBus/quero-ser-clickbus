package com.clickbus.moviesdbtest.movies.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.state.State
import com.clickbus.moviesdbtest.movies.view.adapters.HomeAdapter
import com.clickbus.moviesdbtest.movies.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //txvHome.setOnClickListener {findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment)}


        //Dono do ViewModel
        val viewModel: BaseViewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.popularMovies()
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
                activity?.let { context ->
                    rclHome.apply {
                        rclHome.layoutManager = LinearLayoutManager(context,
                                LinearLayoutManager.VERTICAL,
                                false)
                        adapter = HomeAdapter(state.listMovies)
                    }
                }

            }
            is State.GenreList -> {
                //filtros de generes
            }
            is State.Failure -> {
                "Erro aqui"
            }
        }


    }


}


