package com.mahmoudelshamy.nytimes.features.articles.data.remote

import com.mahmoudelshamy.nytimes.features.articles.data.FakeArticlesData
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticlesResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class FakeArticlesApi @Inject constructor(private val gson: Gson) : ArticlesApi {

    override suspend fun getMostPopularArticles(): Response<ArticlesResponse> =
        with(FakeArticlesData.articlesResponse) {
            return if (first == 200) {
                val articlesResponse = gson.fromJson(second, ArticlesResponse::class.java)
                return Response.success(articlesResponse)
            } else {
                Response.error(first, second.toResponseBody("application/json".toMediaType()))
            }
        }
}