package com.clickbus.moviesdbtest.movies.state

import com.clickbus.moviesdbtest.movies.models.Genre
import com.clickbus.moviesdbtest.movies.models.Movie

sealed class State {
    data class MovieList(var listMovies: List<Movie>): State()
    data class GenreList(var listGenre: List<Genre>): State()
    data class MovieDetail(var MoviesDetails: List<com.clickbus.moviesdbtest.movies.models.MovieDetail>):State()


    data class Failure(var message:String):State()

}