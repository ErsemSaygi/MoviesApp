package com.afac.myapplication23.presentation

sealed class Screen (val route:String) {
    object MovieScreen : Screen("movie_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
    object PopulerMoviesScreen : Screen("populer_movies_screen")
}