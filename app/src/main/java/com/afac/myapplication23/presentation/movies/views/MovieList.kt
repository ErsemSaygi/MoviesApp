package com.afac.myapplication23.presentation.movies.views



import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.afac.myapplication23.domain.model.NowPlayingMovie


import org.intellij.lang.annotations.JdkConstants

@Composable
fun MovieList(
    movie : NowPlayingMovie,
    onItemClick : (NowPlayingMovie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(movie) }
            .padding(0.dp,16.dp),

    ) {
        ImageWithLoadingPlaceholder(
            imageUrl = "https://image.tmdb.org/t/p/original"+movie.poster_path,
            modifier = Modifier
                .padding(16.dp,0.dp,0.dp,0.dp)
                .size(100.dp)
        )


        Column(modifier = Modifier.align(CenterVertically).padding(8.dp),horizontalAlignment = Alignment.Start,
        ) {
            Text(
                movie.title+"("+movie.release_date.substring(0,4)+")",
                lineHeight=18.75.sp,
                fontSize=16.sp,
                fontWeight=FontWeight.Bold,
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                color = Black,
                textAlign = TextAlign.Center

            )

            Text(movie.overview,
                modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp),
                lineHeight=14.06.sp,
                fontSize=12.sp,
                maxLines=4,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                color = Black,
                textAlign = TextAlign.Start
            )

        }


    }
}
@Composable
fun ImageWithLoadingPlaceholder(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    // ImageLoader ile bir resmi yüklemek için ImagePainter'ı hatırla
    val painter = rememberImagePainter(data = imageUrl)
    // Resmin yüklenip yüklenmediğini izlemek için bir durumu hatırla
    val isLoading = painter.state is ImagePainter.State.Loading

    // Box içinde resmi ve yüklenirken gösterilecek CircularProgressIndicator'u göster
    Box(
        modifier = modifier
            .clip(CutCornerShape(6.dp))
            .background(color = White)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier

                .clip(RoundedCornerShape(16.dp))
        )

        // Eğer resim yükleniyorsa CircularProgressIndicator'ı göster
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(75.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)

            )
        }
    }
}