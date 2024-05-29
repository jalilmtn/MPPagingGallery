package com.example.mppaginggallery.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val movieLazyPagingItems = homeViewModel.pagingDataFlow.collectAsLazyPagingItems()

    HomeScreen(
        movieLazyPagingItems
    )
}






