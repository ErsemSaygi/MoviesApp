package com.afac.myapplication23.domain.model

data class MovieDetail(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
) {
}