package com.mahmoudelshamy.nytimes.features.articles.data.repository

import com.mahmoudelshamy.nytimes.core.BaseRepository
import com.mahmoudelshamy.nytimes.features.articles.data.remote.ArticlesApi
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.toArticle
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesApi: ArticlesApi
) : ArticlesRepository, BaseRepository() {

    override suspend fun getMostPopularArticles(): List<Article> {
        val response = handleApiResponse(
            articlesApi.getMostPopularArticles()
        )

        return response.results.map {
            it.toArticle()
        }
    }
}