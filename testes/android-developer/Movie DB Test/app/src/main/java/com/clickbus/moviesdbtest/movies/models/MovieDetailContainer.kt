package com.clickbus.moviesdbtest.movies.models

import com.google.gson.annotations.SerializedName

data class MovieDetailContainer(
        @SerializedName("movie") val movieDetail: List<MovieDetail>
)
