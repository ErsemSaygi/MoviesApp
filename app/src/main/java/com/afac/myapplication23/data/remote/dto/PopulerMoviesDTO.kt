package com.afac.myapplication23.data.remote.dto

import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.model.PopulerMovie

data class PopulerMoviesDTO(
    val page: Int,
    val results: List<ResultXXX>,
    val total_pages: Int,
    val total_results: Int
)
fun PopulerMoviesDTO.toMovieList():List<PopulerMovie>{
    return results.map{
            result ->
        PopulerMovie(result.id,result.overview,result.poster_path,result.release_date,result.title)
    }}