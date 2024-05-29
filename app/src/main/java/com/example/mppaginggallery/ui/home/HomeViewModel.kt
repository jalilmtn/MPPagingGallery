package com.example.mppaginggallery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.mppaginggallery.domain.repo.MoviesPagingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    moviesPagingRepo: MoviesPagingRepo,
) : ViewModel() {

    //Map data to remove duplicated items, we will have issue ny using duplicated key in lazy column
    val pagingDataFlow = moviesPagingRepo.getCats().map {
        val personMap = mutableSetOf<Int>()
        it.filter { movie ->
            if (personMap.contains(movie.id)) {
                false
            } else {
                personMap.add(movie.id)
            }
        }
    }.cachedIn(viewModelScope)
}
