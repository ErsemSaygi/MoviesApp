package com.afac.myapplication23.data.remote.dto

import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.model.UpcomingMovie

data class UpcomingDTO(
    val dates: DatesX,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)
fun UpcomingDTO.toMovieList():List<UpcomingMovie>{
    return results.map{
            result ->
        UpcomingMovie(result.id,result.overview,result.poster_path,result.release_date,result.title)
    }
}