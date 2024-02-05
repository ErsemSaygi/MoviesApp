package com.afac.myapplication23.presentation.movies

import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.model.PopulerMovie

data class PopulerMoviesState (
    val isLoading : Boolean = false,
    val movies : List<PopulerMovie> = emptyList(),
    val error : String = "",


    )