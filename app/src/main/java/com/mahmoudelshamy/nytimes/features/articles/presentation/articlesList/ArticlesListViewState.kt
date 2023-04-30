package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

sealed class ArticlesListViewState {
    object Loading : ArticlesListViewState()
    data class Error(val message: String? = null) : ArticlesListViewState()
    data class Articles(val articles: List<Article>) : ArticlesListViewState()
    object Empty : ArticlesListViewState()
}