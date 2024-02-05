package com.afac.myapplication23.data.repository

import com.afac.myapplication23.data.remote.dto.*
import com.afac.myapplication23.domain.model.MovieDetail
import com.afac.myapplication23.domain.repository.MovieRepository
import com.afac.myapplication23.util.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api:MovieAPI):MovieRepository {
    override suspend fun getNowPlayingMovies(): NowPlayingDTO {
        return api.getNowPlaying()
    }

    override suspend fun getUpcomingMovies(): UpcomingDTO {
        return api.getUpcoming()
    }

    override suspend fun getMovieDetail(id: Int):MovieDetailDTO  {
      return api.getMovieDetails()


    }

    override suspend fun getPopulerMovies(): PopulerMoviesDTO {
        return api.getPopulerMovies()
    }
}