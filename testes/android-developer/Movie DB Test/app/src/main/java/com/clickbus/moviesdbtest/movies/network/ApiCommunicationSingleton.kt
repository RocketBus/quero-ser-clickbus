package com.clickbus.moviesdbtest.movies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCommunicationSingleton {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val tmdbService: TmdbService by lazy { retrofit.create(TmdbService::class.java) }


}