package com.example.mppaginggallery.data.remote

import com.example.mppaginggallery.common.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val request = chain.request()

        val response = chain.proceed(request.addAuthentication(Constants.API_KEY))

//        handle auth error and etc here
//        if (response.code == 401) {
//        }
        response

    }

    private fun Request.addAuthentication(
        key: String,
    ): Request = newBuilder()
        .addHeader(
            "Authorization",
            key
        )
        .build()
}
