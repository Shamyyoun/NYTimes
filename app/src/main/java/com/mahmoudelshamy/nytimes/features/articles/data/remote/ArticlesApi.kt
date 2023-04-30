package com.mahmoudelshamy.nytimes.features.articles.data.remote

import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesApi {
    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun getMostPopularArticles(): Response<ArticlesResponse>
}