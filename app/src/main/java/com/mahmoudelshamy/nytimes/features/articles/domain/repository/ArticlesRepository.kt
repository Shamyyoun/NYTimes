package com.mahmoudelshamy.nytimes.features.articles.domain.repository

import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

interface ArticlesRepository {
    suspend fun getMostPopularArticles(): List<Article>
}