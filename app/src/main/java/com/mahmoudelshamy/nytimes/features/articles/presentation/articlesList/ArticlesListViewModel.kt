package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoudelshamy.nytimes.common.AppError
import com.mahmoudelshamy.nytimes.common.Resource
import com.mahmoudelshamy.nytimes.core.BaseViewModel
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.useCases.GetMostPopularArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    private val getArticles: GetMostPopularArticlesUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ArticlesListViewState>()
    val viewState: LiveData<ArticlesListViewState> = _viewState

    init {
        fetchArticles()
    }

    fun onEvent(event: ArticlesListEvent) = when (event) {
        is ArticlesListEvent.FetchArticles -> fetchArticles()

        is ArticlesListEvent.ArticleClicked -> navigateToArticleDetails(event.article)
    }

    private fun fetchArticles() {
        getArticles().onEach {
            when (it) {
                is Resource.Loading -> _viewState.value = ArticlesListViewState.Loading

                is Resource.Success -> _viewState.value = if (it.data.isNotEmpty()) {
                    ArticlesListViewState.Articles(it.data)
                } else {
                    ArticlesListViewState.Empty
                }

                is Resource.Error -> _viewState.value = ArticlesListViewState.Error(
                    message = if (it.error is AppError.ApiErrorMessage) {
                        it.error.message
                    } else {
                        null
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun navigateToArticleDetails(article: Article) {
        navigate(
            ArticlesListFragmentDirections.actionNavArticlesListToNavArticleDetails(article)
        )
    }
}