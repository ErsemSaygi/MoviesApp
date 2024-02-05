package com.afac.myapplication23.domain.repository

import com.afac.myapplication23.data.remote.dto.MovieDetailDTO
import com.afac.myapplication23.data.remote.dto.NowPlayingDTO
import com.afac.myapplication23.data.remote.dto.PopulerMoviesDTO
import com.afac.myapplication23.data.remote.dto.UpcomingDTO

interface MovieRepository {
    suspend fun  getNowPlayingMovies():NowPlayingDTO
    suspend fun  getUpcomingMovies():UpcomingDTO
    suspend fun  getMovieDetail(id:Int):MovieDetailDTO
    suspend fun  getPopulerMovies():PopulerMoviesDTO
}