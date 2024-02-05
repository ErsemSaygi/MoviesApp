package com.afac.myapplication23.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.afac.myapplication23.presentation.Screen
import com.afac.myapplication23.presentation.movies.UpcomingMoviesViewModel
import com.afac.myapplication23.util.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopulerMoviesScreen(
    navController: NavController,

    viewModelPopuler : NowPlayingMoviesViewModel = hiltViewModel(),




) {
    val systemUiController= rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)
    val statePopuler = viewModelPopuler.state.value



    Box(modifier = Modifier
        .fillMaxSize()


        .background(Color.White)){
Column(modifier = Modifier.fillMaxSize()) {
    TopAppBar(
        modifier = Modifier.padding(top = 20.dp),
        title = {Text(
            text = "Populer Movies",
            modifier = Modifier
                .fillMaxWidth()

              ,
            style = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        ) },
        navigationIcon = {
            IconButton(
                onClick = {navController.navigate(Screen.MovieScreen.route)  }
            ) {
                Icon(modifier=Modifier.padding(0.dp,0.dp,0.dp,9.dp).size(30.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        },

        backgroundColor = Color.White,
        elevation = 0.dp,

        )
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 0.dp, 0.dp, 80.dp)) {


        items(statePopuler.movies) { movie ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 10.dp)
                ,
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                MovieList(movie = movie, onItemClick = {
                    Constants.idMovie =movie.id.toString()
                    navController.navigate(Screen.MovieDetailScreen.route)
                })
            }
        }
    }

}




        if (statePopuler.error.isNotBlank() ) {
            Text(text = statePopuler.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Center)
            )
        }

        if(statePopuler.isLoading ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}
