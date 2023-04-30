package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

sealed class ArticlesListEvent {
    object FetchArticles : ArticlesListEvent()
    data class ArticleClicked(val article: Article) : ArticlesListEvent()
}