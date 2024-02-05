package com.afac.myapplication23.presentation.movies.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afac.myapplication23.domain.use_case.get_movies.GetNowPlayingMoviesUseCase
import com.afac.myapplication23.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
):ViewModel() {

    private val _state= mutableStateOf<NowPlayingMoviesState>(NowPlayingMoviesState())
    val state: State<NowPlayingMoviesState> =_state
    private var job: Job?=null
   init {
    getNowPlayingMovies()
   }
    fun getNowPlayingMovies(){
        job?.cancel()
        job=getNowPlayingMoviesUseCase.executeGetNowPlayingMovies().onEach {
         when(it){
             is Resource.Success ->{
                 _state.value= NowPlayingMoviesState(movies = it.data?: emptyList())
             }
             is Resource.Error ->{
                 _state.value= NowPlayingMoviesState(error = it.message?: "Hata")
             }
             is Resource.Loading ->{
                 _state.value= NowPlayingMoviesState(isLoading = true)
             }
         }
        }.launchIn(viewModelScope)
    }

}