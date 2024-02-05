package com.afac.myapplication23.presentation.movie_detail.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afac.myapplication23.domain.model.MovieDetail
import com.afac.myapplication23.domain.use_case.get_movie_detail.GetMovieDetail
import com.afac.myapplication23.util.Constants

import com.afac.myapplication23.util.Constants.idMovie
import com.afac.myapplication23.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase:GetMovieDetail,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val originalDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val targetDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun formatDateString(originalDate: String): String {
        return try {
            val date = originalDateFormat.parse(originalDate)
            targetDateFormat.format(date!!)
        } catch (e: Exception) {
            // Hata durumu için bir şey yapabilirsiniz, örneğin orijinal tarih formatı geçerli değilse
            originalDate
        }
    }
    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        /*if(idMovie.isNotEmpty()){
            getMovieDetail(idMovie)
        }*/

        makeApiCall()
    }
private fun getMovieDetail(id:String){
     getMovieDetailsUseCase.executeGetMovieDetail(id =Integer.valueOf(id) ).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")

                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
}

    private fun makeApiCall() {
        // API çağrısını başlat
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = getMovieDetail1()
                val jsonObject = JSONObject(response)
                var movieDetail=MovieDetail(jsonObject.getInt("id"),
                    jsonObject.getString("overview"),
                            jsonObject.getString("poster_path"),
                            jsonObject.getString("release_date"),
                            jsonObject.getString("title"),
                            jsonObject.getDouble("vote_average"),

                    )

                _state.value=MovieDetailState(movie = movieDetail)
                MovieDetailState(isLoading = false)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private fun getMovieDetail1() :String{
        val client = OkHttpClient()
     MovieDetailState(isLoading = true)
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/"+ idMovie+"?language=en-US")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMTJlZTk0NDBiMDFiM2ZlZDUwZGJiNjIxNTE4ZDg0YyIsInN1YiI6IjY1YWE2OTI0YzQzM2VhMDBjZTc0OTY2ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9_G1oHwi0ABevPLs3GlPF4zz-BhxW099JCfaaFmyQwQ")
            .build()

        val response = client.newCall(request).execute()
        val a=response.body?.string();
        return a.toString()

    }

}