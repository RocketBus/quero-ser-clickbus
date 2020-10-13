package com.clickbus.moviesdbtest.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clickbus.moviesdbtest.BuildConfig
import com.clickbus.moviesdbtest.movies.models.GenreListContainer
import com.clickbus.moviesdbtest.movies.models.MovieDetail
import com.clickbus.moviesdbtest.movies.models.MovieDetailContainer
import com.clickbus.moviesdbtest.movies.models.MovieListPageResult
import com.clickbus.moviesdbtest.movies.network.ApiCommunicationSingleton
import com.clickbus.moviesdbtest.movies.state.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseViewModel : ViewModel(){

    val state: MutableLiveData<State> = MutableLiveData()
    private val repository = ApiCommunicationSingleton

    fun popularMovies(){
        repository.tmdbService.trendingMovies(BuildConfig.API_KEY).enqueue(
                object : Callback<MovieListPageResult> {
                    override fun onResponse(
                            call: Call<MovieListPageResult>,
                            response: Response<MovieListPageResult>
                    ) {
                        response.body()?.movieList?.let {
                            state.value = State.MovieList(it)
                            return

                        }
                        state.value = State.Failure("")
                    }

                    override fun onFailure(call: Call<MovieListPageResult>, t: Throwable) {
                        state.value = t.message?.let { State.Failure(it) }
                    }
                })
        
    }
    

    fun genre(){
       
        repository.tmdbService.genres(BuildConfig.API_KEY).enqueue(object : Callback<GenreListContainer> {
            override fun onResponse(
                    call: Call<GenreListContainer>,
                    response: Response<GenreListContainer>
            ) {
                response.body()?.genres?.let {
                    state.value = State.GenreList(it)
                    return
                }
              state.value = State.Failure("")
            }

            override fun onFailure(call: Call<GenreListContainer>, t: Throwable) {
               state.value = t.message?.let { State.Failure(it) }
            }
        })
    }
 /*
    fun MovieDetail(){
        repository.tmdbService.movieDetail(18,BuildConfig.API_KEY,"en-US").enqueue(object : Callback<MovieDetailContainer> {
            override fun onResponse(call: Call<MovieDetailContainer>, response: Response<MovieDetailContainer>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MovieDetailContainer>, t: Throwable) {
                TODO("Not yet implemented")
            }


        }

  */


}
