package com.afac.myapplication23.domain.use_case.get_movie_detail

import com.afac.myapplication23.data.remote.dto.toMovieDetail
import com.afac.myapplication23.data.remote.dto.toMovieList
import com.afac.myapplication23.domain.model.MovieDetail
import com.afac.myapplication23.domain.model.NowPlayingMovie
import com.afac.myapplication23.domain.repository.MovieRepository
import com.afac.myapplication23.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetail@Inject constructor(private val repository : MovieRepository) {
    fun executeGetMovieDetail(id:Int): Flow<Resource<MovieDetail>> = flow{
        try {
            emit(Resource.Loading())
            val movie=repository.getMovieDetail(id =id ).toString()
            //emit(Resource.Success(movie))
        }catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}