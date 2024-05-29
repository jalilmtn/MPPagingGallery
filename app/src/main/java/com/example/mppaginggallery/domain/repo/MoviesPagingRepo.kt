package com.example.mppaginggallery.domain.repo

import androidx.paging.PagingData
import com.example.mppaginggallery.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesPagingRepo {
    fun getCats():  Flow<PagingData<Movie>>
}