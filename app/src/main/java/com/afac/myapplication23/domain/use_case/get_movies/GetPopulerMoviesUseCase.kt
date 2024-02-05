package com.afac.myapplication23.domain.use_case.get_movies

import com.afac.myapplication23.data.remote.dto.toMovieList
import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.model.PopulerMovie
import com.afac.myapplication23.domain.repository.MovieRepository
import com.afac.myapplication23.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetPopulerMoviesUseCase @Inject constructor(private val repository : MovieRepository){
    fun executeGetPopulerMovies(): Flow<Resource<List<PopulerMovie>>> = flow{
        try {
            emit(Resource.Loading())
            val movieList=repository.getPopulerMovies()
            emit(Resource.Success(movieList.toMovieList()))
        }catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }
}