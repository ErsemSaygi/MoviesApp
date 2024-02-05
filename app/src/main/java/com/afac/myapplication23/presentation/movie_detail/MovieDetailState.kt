package com.afac.myapplication23.presentation.movie_detail.views

import com.afac.myapplication23.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)