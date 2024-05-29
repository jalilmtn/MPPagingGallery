package com.example.mppaginggallery.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val title: String,
    val video: Boolean,
    @SerializedName("poster_path")
    val posterPath: String,
)


