package com.movieland.data.repository.movie

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.datasource.movie.MovieDataSource
import com.movieland.data.mapper.toMovieNowPlayed
import com.movieland.data.mapper.toMoviePopular
import com.movieland.data.mapper.toMovieTopRated
import com.movieland.data.mapper.toMovieUpcoming
import com.movieland.data.model.Movie
import kotlinx.coroutines.flow.Flow


class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {
    override fun getNowPlaying(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return proceedFlow {
            dataSource.getNowPlaying(page).results.toMovieNowPlayed()
        }
    }

    override fun getPopular(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return proceedFlow {
            dataSource.getPopular(page).results.toMoviePopular()
        }
    }

    override fun getTopRating(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return proceedFlow {
            dataSource.getTopRating(page).results.toMovieTopRated()
        }
    }

    override fun getUpcoming(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return proceedFlow {
            dataSource.getUpcoming(page).results.toMovieUpcoming()
        }
    }
}