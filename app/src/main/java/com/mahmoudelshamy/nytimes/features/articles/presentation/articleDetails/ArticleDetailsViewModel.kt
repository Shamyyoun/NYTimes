package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahmoudelshamy.nytimes.core.BaseViewModel
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor() : BaseViewModel() {
    private var article: Article? = null

    private val _viewState = MutableLiveData<ArticleDetailsViewState>()
    val viewState: LiveData<ArticleDetailsViewState> = _viewState

    fun init(article: Article) {
        this.article = article
        _viewState.value = ArticleDetailsViewState.ArticleDetails(article)
    }
}