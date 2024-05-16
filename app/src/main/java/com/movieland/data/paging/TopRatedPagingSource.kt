package com.movieland.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movieland.data.mapper.toMovieTopRated
import com.movieland.data.model.Movie
import com.movieland.data.source.network.services.MovielandApiService

class TopRatedPagingSource(private val service: MovielandApiService) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = service.topRated(page)
            LoadResult.Page(
                data = response.results.toMovieTopRated(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results?.isEmpty()== true) null else page.plus(1),
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}