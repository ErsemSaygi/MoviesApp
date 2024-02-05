package com.afac.myapplication23.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.afac.myapplication23.domain.model.UpcomingMovie
import com.afac.myapplication23.presentation.Screen
import com.afac.myapplication23.presentation.movies.UpcomingMoviesViewModel
import com.afac.myapplication23.util.Constants.idMovie
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieScreen(
    navController: NavController,

    viewModelNow : NowPlayingMoviesViewModel = hiltViewModel(),
    viewModelUpcoming: UpcomingMoviesViewModel = hiltViewModel()



) {
     val systemUiController= rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)
    val stateNow = viewModelNow.state.value
    val stateUpcoming = viewModelUpcoming.state.value


    val pullRefreshState = rememberPullRefreshState(
        refreshing = stateUpcoming.isLoading,
        onRefresh = {viewModelUpcoming.getUpcomingMovies()
        viewModelNow.getNowPlayingMovies()
        })
    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)

        .background(Color.White)){
        LazyColumn(modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp,80.dp)) {
            item {
                if (stateUpcoming.movies.isNotEmpty()) {
                    ViewPagerSlider(
                        stateUpcoming.movies.subList(0, 6),

                        onItemClick = { clickedPosition ->
                            idMovie=stateUpcoming.movies[clickedPosition].id.toString()
                            navController.navigate(Screen.MovieDetailScreen.route )
                        }
                    )
                }
            }

            items(stateNow.movies) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp,10.dp)
                        ,
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    MovieList(movie = movie, onItemClick = {
                        idMovie=movie.id.toString()
                        navController.navigate(Screen.MovieDetailScreen.route)
                    })
                }
            }
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            PullRefreshIndicator(
                refreshing = viewModelUpcoming.state.value.isLoading,
                state = pullRefreshState,
                backgroundColor = Color.White,
            )
        }

        if (stateNow.error.isNotBlank() && stateUpcoming.error.isNotBlank()) {
            Text(text = stateNow.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Center)
            )
        }

        if(stateNow.isLoading && stateUpcoming.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
        }

    }
    }
