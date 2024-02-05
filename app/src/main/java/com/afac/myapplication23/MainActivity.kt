package com.afac.myapplication23

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afac.myapplication23.presentation.Screen
import com.afac.myapplication23.presentation.movie_detail.views.MovieDetailScreen
import com.afac.myapplication23.presentation.movies.views.MovieScreen
import com.afac.myapplication23.presentation.movies.views.PopulerMoviesScreen
import com.afac.myapplication23.ui.theme.MyApplication23Theme
import com.afac.myapplication23.util.Constants.idMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyApplication23Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.MovieScreen.route
                    ) {
                        composable(route = Screen.MovieScreen.route) {
                            MovieScreen(navController = navController)
                        }
                        composable(route = Screen.MovieDetailScreen.route) {
                            MovieDetailScreen(navController = navController)
                        }
                        composable(route = Screen.PopulerMoviesScreen.route) {
                            PopulerMoviesScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

