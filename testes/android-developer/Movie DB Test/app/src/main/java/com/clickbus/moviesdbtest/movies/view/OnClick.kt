package com.clickbus.moviesdbtest.movies.view

import com.clickbus.moviesdbtest.movies.models.Movie

interface OnClick {
        fun onCellClickListener(movie: Movie)
}