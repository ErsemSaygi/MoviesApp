package com.afac.myapplication23.data.remote.dto

import com.afac.myapplication23.domain.model.MovieDetail


data class MovieDetailDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<GenreX>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyX>,
    val production_countries: List<ProductionCountryX>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageX>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
fun MovieDetailDTO.toMovieDetail():MovieDetail{
    return MovieDetail(id,overview,poster_path,release_date,title,vote_average)

}