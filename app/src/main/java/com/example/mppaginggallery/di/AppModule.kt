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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

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
    fun providePagingSource(
        movieApi: MovieApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): MoviePagingSource {
        return MoviePagingSource(movieApi, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideCatPagingSource(moviePagingSource: MoviePagingSource): MoviesPagingRepo {
        return MoviesPagingRepoImpl(moviePagingSource)
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher