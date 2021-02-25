package com.clickbus.moviesdbtest.movies

import com.clickbus.moviesdbtest.BuildConfig
import com.clickbus.moviesdbtest.movies.models.Movie
import com.clickbus.moviesdbtest.movies.models.MovieDetail
import com.clickbus.moviesdbtest.movies.network.ApiCommunicationSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class Repository {

    private var language = "${Locale.getDefault().language}-${Locale.getDefault().country}"

    private var msg = "Ops.. Ocorreu uma Falha ao carregar"

    private val service = ApiCommunicationSingleton.tmdbService

    suspend fun Movies(genres: Array<Int> = emptyArray(), page: Int): List<Movie> = withContext(Dispatchers.IO){
        val response = service.popularMovies(
            BuildConfig.API_KEY, language,genres.joinToString(separator=","){"$it"}, page).execute()

        if(response.isSuccessful){
            response.body()?.movieList!!
        }else throw Exception("$msg os Filmes")
    }

    suspend fun MoviesDetails(id:Int): MovieDetail = withContext(Dispatchers.IO){
        val response = service.movieDetail(id,
            BuildConfig.API_KEY, language).execute()

         if(response.isSuccessful){
            response.body() as MovieDetail
        }else throw Exception("$msg os Detalhes do Filme")
    }

    suspend fun Genders() = withContext(Dispatchers.IO) {
        val response = service.genres(BuildConfig.API_KEY,language).execute()

        if (response.isSuccessful) {
            response.body()?.genres
        } else throw Exception("$msg a lista de Gêneros")
    }




}