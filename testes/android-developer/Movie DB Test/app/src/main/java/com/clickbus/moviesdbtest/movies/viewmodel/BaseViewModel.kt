package com.clickbus.moviesdbtest.movies.viewmodel

import androidx.lifecycle.*
import com.clickbus.moviesdbtest.BuildConfig
import com.clickbus.moviesdbtest.movies.Repository
import com.clickbus.moviesdbtest.movies.models.*
import com.clickbus.moviesdbtest.movies.network.ApiCommunicationSingleton
import com.clickbus.moviesdbtest.movies.state.State
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseViewModel(val repository: Repository) : ViewModel(){

    class BaseViewModelFactory():ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = Repository()
            return BaseViewModel(repository) as T
        }

    }


    private val _movies = MutableLiveData<State.Message<List<Movie>>>()
    val movies: LiveData<State.Message<List<Movie>>>
        get() = _movies

    private val _movie = MutableLiveData<State.Message<MovieDetail>>()
    val movie: LiveData<State.Message<MovieDetail>>
        get() = _movie



    fun upMovies() {
        _movies.value = State.Message(State.Status.START)

        viewModelScope.launch {
            try {
                val movies = repository.Movies()
                _movies.value = State.Message(State.Status.COMPLETE, movies)

            } catch(e: Exception) {
                _movies.value = State.Message(State.Status.ERROR, error=e)
            }
        }
    }

    fun uphMovieDetails(id: Int) {
        _movie.value = State.Message(State.Status.START)

        viewModelScope.launch {
            try {
                val movie = repository.MoviesDetails(id)
                _movie.value = State.Message(State.Status.COMPLETE, movie)

            } catch(e: Exception) {
                _movie.value = State.Message(State.Status.ERROR, error=e)
            }
        }
    }
}
