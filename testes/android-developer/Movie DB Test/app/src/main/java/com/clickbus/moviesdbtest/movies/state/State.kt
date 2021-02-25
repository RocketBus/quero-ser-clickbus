package com.clickbus.moviesdbtest.movies.state


object State {
    /*
    data class MovieList(var listMovies: List<Movie>): State()
    data class GenreList(var listGenre: List<Genre>): State()
    data class MovieDetail(var MoviesDetails: List<com.clickbus.moviesdbtest.movies.models.MovieDetail>):State()


    data class Failure(var message:String):State()

     */

    enum class Status{
        START, COMPLETE, ERROR
    }

    class Message<out T>(
        val status: Status,
        val data:   T? = null,
        val error:  Exception? = null
    ){
        val success: Boolean? =
            (status != Status.COMPLETE && status != Status.ERROR) && error != null

        val finished: Boolean =
            (status == Status.COMPLETE || status == Status.ERROR)

    }

}