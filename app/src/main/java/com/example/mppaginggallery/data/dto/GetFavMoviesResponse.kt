package com.example.mppaginggallery.data.dto

import com.google.gson.annotations.SerializedName

data class GetFavMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)