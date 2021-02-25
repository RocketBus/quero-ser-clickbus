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

class BaseViewModel( val repository: Repository) : ViewModel(){

    class BaseViewModelFactory():ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = Repository()
            return BaseViewModel(repository) as T
        }

    }


    private val _movies = MutableLiveData<State.Message<List<Movie>>>()
    val movies: LiveData<State.Message<List<Movie>>>
        get() = _movies

    private val _movieDetail = MutableLiveData<State.Message<MovieDetail>>()
    val movie: LiveData<State.Message<MovieDetail>>
        get() = _movieDetail

    private val _genders = MutableLiveData<State.Message<List<Genre>>>()
    val genders: LiveData<State.Message<List<Genre>>>
        get() = _genders



    fun upMovies(genres: Array<Int> = emptyArray(), page: Int) {
        _movies.value = State.Message(State.Status.START)

        viewModelScope.launch {
            try {
                val movies = repository.Movies(genres,page)
                _movies.value = State.Message(State.Status.COMPLETE, movies)

            } catch(e: Exception) {
                _movies.value = State.Message(State.Status.ERROR, error=e)
            }
        }
    }

    fun uphMovieDetails(id: Int) {
        _movieDetail.value = State.Message(State.Status.START)

        viewModelScope.launch {
            try {
                val movie = repository.MoviesDetails(id)
                _movieDetail.value = State.Message(State.Status.COMPLETE, movie)

            } catch(e: Exception) {
                _movieDetail.value = State.Message(State.Status.ERROR, error=e)
            }
        }
    }

    fun upGenders() {
        _genders.value = State.Message(State.Status.START)

        viewModelScope.launch {
            try {
                val genders = repository.Genders()
                _genders.value = State.Message(State.Status.COMPLETE, genders)

            } catch(e: Exception) {
                _genders.value = State.Message(State.Status.ERROR, error=e)
            }
        }
    }
}
