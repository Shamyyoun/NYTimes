package com.mahmoudelshamy.nytimes.features.articles.domain.useCases

import com.mahmoudelshamy.nytimes.common.Resource
import com.mahmoudelshamy.nytimes.core.BaseUseCase
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostPopularArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : BaseUseCase() {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        tryFlow {
            // Loading
            emit(
                Resource.Loading()
            )

            // Get articles
            val articles = articlesRepository.getMostPopularArticles()
            emit(
                Resource.Success(
                    data = articles
                )
            )
        }
    }
}