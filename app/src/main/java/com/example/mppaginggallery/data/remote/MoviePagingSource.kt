package com.example.mppaginggallery.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mppaginggallery.data.dto.toMovie
import com.example.mppaginggallery.di.IoDispatcher
import com.example.mppaginggallery.domain.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val movieApi: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return withContext(ioDispatcher) {
            try {
                val page = params.key ?: 1
                val response = movieApi.getFavMovies(page = page).results.map { it.toMovie() }

                LoadResult.Page(
                    data = response,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.isEmpty()) null else page.plus(1),
                )

                //TODO: check error codes from api and handle them properly
            } catch (e: IOException) {
                LoadResult.Error(Throwable("Check your internet connection."))
            } catch (e: HttpException) {
                LoadResult.Error(Throwable("Something went wrong, please try again."))
            }
        }
    }

    companion object {
        const val PAGE_LIMIT = 10
    }
}