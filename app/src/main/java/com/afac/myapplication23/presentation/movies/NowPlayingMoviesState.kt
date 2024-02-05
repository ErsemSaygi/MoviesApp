package com.afac.myapplication23.presentation.movies.views


import com.afac.myapplication23.domain.model.NowPlayingMovie

data class NowPlayingMoviesState (
    val isLoading : Boolean = false,
    val movies : List<NowPlayingMovie> = emptyList(),
    val error : String = "",


    )