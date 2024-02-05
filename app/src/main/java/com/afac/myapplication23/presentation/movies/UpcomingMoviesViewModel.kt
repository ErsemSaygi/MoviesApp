package com.afac.myapplication23.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afac.myapplication23.domain.use_case.get_movies.GetNowPlayingMoviesUseCase
import com.afac.myapplication23.domain.use_case.get_movies.GetUpcomingMoviesUseCase
import com.afac.myapplication23.presentation.movies.views.NowPlayingMoviesState
import com.afac.myapplication23.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
):ViewModel() {

    private val _state= mutableStateOf<UpcomingMoviesState>(UpcomingMoviesState())
    val state: State<UpcomingMoviesState> =_state
    private var job: Job?=null
    init {
        getUpcomingMovies()
    }
     fun getUpcomingMovies(){
        job?.cancel()
        job=getUpcomingMoviesUseCase.executeGetUpcomingMovies().onEach {
            when(it){
                is Resource.Success ->{
                    _state.value= UpcomingMoviesState(movies = it.data?: emptyList())
                }
                is Resource.Error ->{
                    _state.value= UpcomingMoviesState(error = it.message?: "Hata")
                }
                is Resource.Loading ->{
                    _state.value= UpcomingMoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}