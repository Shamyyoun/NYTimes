package com.mahmoudelshamy.nytimes.features.articles.domain.useCases

import app.cash.turbine.test
import com.mahmoudelshamy.nytimes.features.common.domain.AppError
import com.mahmoudelshamy.nytimes.features.common.domain.Result
import com.mahmoudelshamy.nytimes.features.common.domain.exceptions.ErrorResponseException
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.given
import org.powermock.api.mockito.PowerMockito
import java.io.IOException

@ExperimentalCoroutinesApi
class GetMostPopularArticlesUseCaseTest {
    private val articlesRepository = PowerMockito.mock(ArticlesRepository::class.java)
    private val getMostPopularArticlesUseCase = GetMostPopularArticlesUseCase(articlesRepository)

    @Test
    fun `use case is emitting loading`() = runTest {
        // Given

        // When
        val flow = getMostPopularArticlesUseCase()

        // Then
        flow.test {
            assertTrue(
                awaitItem() is Result.Loading
            )

            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `use case is emitting success with articles list when repository return articles`() =
        runTest {
            // Given
            val articles = listOf(
                Article(title = "Article 1 title"),
                Article(title = "Article 2 title")
            )
            PowerMockito.doReturn(articles).`when`(articlesRepository).getMostPopularArticles()

            // When
            val flow = getMostPopularArticlesUseCase()

            // Then
            flow.test {
                assertTrue(
                    awaitItem() is Result.Loading
                )

                val successState = awaitItem()
                assertTrue(successState is Result.Success)

                val emittedArticles = (successState as Result.Success<List<Article>>).data
                assertTrue(emittedArticles.size == 2)
                assertEquals("Article 1 title", emittedArticles[0].title)
                assertEquals("Article 2 title", emittedArticles[1].title)

                awaitComplete()
            }
        }

    @Test
    fun `use case is emitting ApiErrorMessage when repository throw ErrorResponseException`() =
        runTest {
            // Given
            given(articlesRepository.getMostPopularArticles()).willAnswer {
                throw ErrorResponseException(statusCode = 400, message = "Error happened")
            }

            // When
            val flow = getMostPopularArticlesUseCase()

            // Then
            flow.test {
                assertTrue(
                    awaitItem() is Result.Loading
                )

                val errorState = awaitItem()
                assertTrue(errorState is Result.Error)

                val appError = (errorState as Result.Error).error
                assertTrue(appError is AppError.ApiErrorMessage)

                val apiError = appError as AppError.ApiErrorMessage
                assertEquals(400, apiError.statusCode)
                assertEquals("Error happened", apiError.message)

                awaitComplete()
            }
        }

    @Test
    fun `use case is emitting NetworkError when repository throw IOException`() = runTest {
        // Given
        given(articlesRepository.getMostPopularArticles()).willAnswer {
            throw IOException()
        }

        // When
        val flow = getMostPopularArticlesUseCase()

        // Then
        flow.test {
            assertTrue(
                awaitItem() is Result.Loading
            )

            val errorState = awaitItem()
            assertTrue(errorState is Result.Error)

            val appError = (errorState as Result.Error).error
            assertTrue(appError is AppError.NetworkError)

            awaitComplete()
        }
    }

    @Test
    fun `use case is emitting GeneralError when repository throw Throwable`() = runTest {
        // Given
        given(articlesRepository.getMostPopularArticles()).willAnswer {
            throw Throwable()
        }

        // When
        val flow = getMostPopularArticlesUseCase()

        // Then
        flow.test {
            assertTrue(
                awaitItem() is Result.Loading
            )

            val errorState = awaitItem()
            assertTrue(errorState is Result.Error)

            val appError = (errorState as Result.Error).error
            assertTrue(appError is AppError.GeneralError)

            awaitComplete()
        }
    }
}