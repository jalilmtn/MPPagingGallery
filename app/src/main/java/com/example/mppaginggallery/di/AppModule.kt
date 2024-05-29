package com.example.mppaginggallery.di

import com.example.mppaginggallery.common.Constants
import com.example.mppaginggallery.data.remote.AuthenticationInterceptor
import com.example.mppaginggallery.data.remote.MovieApi
import com.example.mppaginggallery.data.remote.MoviePagingSource
import com.example.mppaginggallery.data.repo.MoviesPagingRepoImpl
import com.example.mppaginggallery.domain.repo.MoviesPagingRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun client(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePagingSource(movieApi: MovieApi): MoviePagingSource {
        return MoviePagingSource(movieApi)
    }

    @Provides
    @Singleton
    fun provideCatPagingSource(catsPagingSource: MoviePagingSource): MoviesPagingRepo {
        return MoviesPagingRepoImpl(catsPagingSource)
    }
}