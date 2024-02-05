package com.afac.myapplication23.presentation.movie_detail.views

import android.graphics.ColorFilter
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.AddCircle

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.afac.myapplication23.presentation.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = movieDetailViewModel.state.value
    val systemUiController= rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White)
        ) {LazyColumn(
        modifier = Modifier

            .fillMaxWidth()
    ) {
        item() {
            state.movie?.let {

                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original"+it.poster_path),
                        contentDescription = it.title,
                        contentScale= ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.2f / 1f)// 1.0f, kare görüntü oluşturmak için; en-boy oranını istediğiniz değere ayarlayabilirsiniz

                    )

                    Row(modifier = Modifier.align(Alignment.Start).padding(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier
                                .size(20.dp)

                        )
                        Text(
                            text = it.vote_average.toString().substring(0,3),
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Text(
                            text = "/10",
                            style =  MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                            color = Color.Gray
                        )
                       /* Icon(

                            imageVector = Icons.Rounded.AddCircle,
                            contentDescription = "DOT",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier
                                .size(10.dp)

                        )*/
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text =movieDetailViewModel.formatDateString(it.release_date),
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                    }


                }
                Column(


                ) {



                    Text(text = it.title+"("+it.release_date.substring(0,4)+")",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(14.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    )



                    Text(text = it.overview,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(14.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),

                    )




                }


                if (state.error.isNotBlank()) {
                    Text(text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)

                    )
                }

                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }



    }
        TopAppBar(
            modifier = Modifier.padding(top = 20.dp),
            title = { },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.MovieScreen.route)
                    }
                ) {
                    Icon(modifier=Modifier.padding(9.dp,0.dp,9.dp,20.dp).size(25.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,

            )
        Button(
            onClick = {
                navController.navigate(Screen.PopulerMoviesScreen.route)
            },
            modifier = Modifier
                //.height(48.dp)
                .fillMaxWidth()
                .background(Color.White)

                .padding(16.dp,32.dp,16.dp,50.dp).align(Alignment.BottomCenter),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor =Color(0xFFFFDC5E), // Arka plan rengi
                contentColor = Color.Black// İçerik rengi
            )
        ) {
            Text(text = "Discover Popular Movies", )
        }}}









