package com.mahmoudelshamy.nytimes.features.articles.domain.useCases

import com.mahmoudelshamy.nytimes.features.common.domain.Result
import com.mahmoudelshamy.nytimes.features.common.domain.base.BaseUseCase
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostPopularArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : BaseUseCase() {

    operator fun invoke(): Flow<Result<List<Article>>> = flow {
        tryFlow {
            // Loading
            emit(
                Result.Loading()
            )

            // Get articles
            val articles = articlesRepository.getMostPopularArticles()
            emit(
                Result.Success(
                    data = articles
                )
            )
        }
    }
}