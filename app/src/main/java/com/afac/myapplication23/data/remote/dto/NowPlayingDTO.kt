package com.afac.myapplication23.data.remote.dto

import com.afac.myapplication23.domain.model.NowPlayingMovie

data class NowPlayingDTO(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
fun NowPlayingDTO.toMovieList():List<NowPlayingMovie>{
    return results.map{
        result ->NowPlayingMovie(result.id,result.overview,result.poster_path,result.release_date,result.title)
    }
}