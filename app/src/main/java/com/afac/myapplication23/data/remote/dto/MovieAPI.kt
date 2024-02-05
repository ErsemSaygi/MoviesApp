package com.afac.myapplication23.data.remote.dto

import com.afac.myapplication23.util.Constants
import com.afac.myapplication23.util.Constants.ACCESS_TOKEN
import com.afac.myapplication23.util.Constants.API_KEY
import com.afac.myapplication23.util.Constants.idMovie
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/now_playing?language=en-US&page=1")

    suspend fun getNowPlaying(
        @Query("api_key") apiKey:String=API_KEY
    ):NowPlayingDTO


    @GET("movie/upcoming?language=en-US&page=1")

    suspend fun getUpcoming( @Query("api_key") apiKey:String=API_KEY):UpcomingDTO

    @GET("movie/popular?language=en-US&page=1")

    suspend fun getPopulerMovies( @Query("api_key") apiKey:String=API_KEY):PopulerMoviesDTO

    @GET("{url}")
    fun getMovieDetails(

        @Path("url") movie: String= "movie/$idMovie?language=en-US&page=1",

        @Query("api_key") apiKey:String= API_KEY
    ):MovieDetailDTO
}