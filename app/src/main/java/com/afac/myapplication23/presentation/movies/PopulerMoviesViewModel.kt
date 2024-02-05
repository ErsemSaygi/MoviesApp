package com.afac.myapplication23.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afac.myapplication23.domain.use_case.get_movies.GetNowPlayingMoviesUseCase
import com.afac.myapplication23.domain.use_case.get_movies.GetPopulerMoviesUseCase
import com.afac.myapplication23.presentation.movies.views.NowPlayingMoviesState
import com.afac.myapplication23.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class PopulerMoviesViewModel @Inject constructor(
    private val getPopulerMoviesUseCase: GetPopulerMoviesUseCase
):ViewModel() {

    private val _state= mutableStateOf<PopulerMoviesState>(PopulerMoviesState())
    val state: State<PopulerMoviesState> =_state
    private var job: Job?=null
    init {
        getPopulerMovies()
    }
    private fun getPopulerMovies(){
        job?.cancel()
        job=getPopulerMoviesUseCase.executeGetPopulerMovies().onEach {
            when(it){
                is Resource.Success ->{
                    _state.value= PopulerMoviesState(movies = it.data?: emptyList())
                }
                is Resource.Error ->{
                    _state.value= PopulerMoviesState(error = it.message?: "Hata")
                }
                is Resource.Loading ->{
                    _state.value= PopulerMoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}