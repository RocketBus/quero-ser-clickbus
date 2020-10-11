package com.clickbus.moviesdbtest.movies.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.clickbus.moviesdbtest.R
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.models.MovieListPageResult
import com.clickbus.moviesdbtest.movies.view.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun UI(movies:MovieListPageResult){

         rclHome.apply {
             layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
             //adapter = HomeAdapter()

         }




    }





}