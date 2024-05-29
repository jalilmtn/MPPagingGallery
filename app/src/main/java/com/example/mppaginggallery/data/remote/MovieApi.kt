package com.example.mppaginggallery.data.remote

import com.example.mppaginggallery.data.dto.GetFavMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("discover/movie?")
    suspend fun getFavMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("include_video") includeVideo: Boolean = false,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): GetFavMoviesResponse
}