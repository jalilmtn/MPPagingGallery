package com.example.mppaginggallery.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mppaginggallery.data.remote.MoviePagingSource
import com.example.mppaginggallery.data.remote.MoviePagingSource.Companion.PAGE_LIMIT
import com.example.mppaginggallery.domain.repo.MoviesPagingRepo
import javax.inject.Inject

class MoviesPagingRepoImpl @Inject constructor(private val pagingSource: MoviePagingSource,) : MoviesPagingRepo {
    override fun getCats() = Pager(
        config = PagingConfig(
            pageSize = PAGE_LIMIT,
        ),
        pagingSourceFactory = {
            pagingSource
        }
    ).flow
}