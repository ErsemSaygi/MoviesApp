package com.afac.myapplication23.presentation.movies.views


import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.afac.myapplication23.domain.model.UpcomingMovie
import com.afac.myapplication23.domain.model.imageList
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue



@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerSlider(movieListUpcoming:List<UpcomingMovie>,
    onItemClick: (Int) -> Unit){

    val pagerState  = rememberPagerState(
        pageCount = movieListUpcoming.size,
        initialPage =  2
    )

    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(4000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(modifier = Modifier .background(Color.White)) {

        HorizontalPager(state = pagerState,
            modifier = Modifier
        ) { page ->
            Card(modifier = Modifier.clickable { onItemClick(page)
                println("Tıklanan pozisyon: $page") }
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .fillMaxWidth(),
            ) {

                val movies = movieListUpcoming[page]
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                ) {
                    Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/original"+movies.poster_path),
                        contentDescription = movies.title,
                        contentScale=ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.2f / 1f)// 1.0f, kare görüntü oluşturmak için; en-boy oranını istediğiniz değere ayarlayabilirsiniz

                    )

                    Column(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(15.dp)
                    ) {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign=TextAlign.Center,
                            text = movies.title+"("+movies.release_date.substring(0,4)+")",
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )


//                        AndroidView(factory ={ratingBar},
//                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
//                        )
                        Text(
                            overflow= TextOverflow.Ellipsis,
                            maxLines=1,
                            text = movies.overview,
                            style = MaterialTheme.typography.body2,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                        )
                        HorizontalPagerIndicator(
                            inactiveColor=Color.Gray,
                            activeColor=Color.White,
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),

                        )

                    }

                }


            }

        }

        //Horizontal dot indicator


    }

}

