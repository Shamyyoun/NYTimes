package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails

import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

sealed class ArticleDetailsViewState {
    data class ArticleDetails(val article: Article) : ArticleDetailsViewState()
}