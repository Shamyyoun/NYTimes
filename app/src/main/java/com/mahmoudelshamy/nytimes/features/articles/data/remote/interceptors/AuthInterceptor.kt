package com.mahmoudelshamy.nytimes.features.articles.data.remote.interceptors

import com.mahmoudelshamy.nytimes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url
            .newBuilder()
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder().url(newUrl).build()

        // Continue the request
        return chain.proceed(newRequest.newBuilder().build())
    }
}