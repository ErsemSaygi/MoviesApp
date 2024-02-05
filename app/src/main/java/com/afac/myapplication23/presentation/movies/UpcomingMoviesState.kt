package com.afac.myapplication23.presentation.movies

import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.model.UpcomingMovie

data class UpcomingMoviesState  (
    val isLoading : Boolean = false,
    val movies : List<UpcomingMovie> = emptyList(),
    val error : String = "",
        )
