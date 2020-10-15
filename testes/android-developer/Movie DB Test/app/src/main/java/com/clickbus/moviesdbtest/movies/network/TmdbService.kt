package com.clickbus.moviesdbtest.movies.network

import com.clickbus.moviesdbtest.movies.models.GenreListContainer
import com.clickbus.moviesdbtest.movies.models.MovieDetail
import com.clickbus.moviesdbtest.movies.models.MovieListPageResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("trending/movie/day")
    fun trendingMovies(@Query("api_key") apiKey: String): Call<MovieListPageResult>

    @GET("trending/movie/day")
    fun trendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieListPageResult>

    @GET("discover/movie?sort_by=popularity.desc")
    fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_genres") genres: String = "",
        @Query("page") page: Int,
    ): Call<MovieListPageResult>

    @GET("movie/{id}")
    fun movieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieDetail>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): Call<MovieListPageResult>

    @GET("/movie/now_playing")
    fun moviesInTheaters(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieListPageResult>

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenreListContainer>

    @GET("genre/movie/list")
    fun genresWithLanguage(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenreListContainer>


}