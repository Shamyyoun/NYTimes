package com.mahmoudelshamy.nytimes.features.articles.data.repository

import com.mahmoudelshamy.nytimes.features.common.domain.exceptions.ErrorResponseException
import com.mahmoudelshamy.nytimes.core.mockErrorResponse
import com.mahmoudelshamy.nytimes.features.articles.data.remote.ArticlesApi
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticleResult
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticlesResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.powermock.api.mockito.PowerMockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class ArticlesRepositoryImplTest {
    private val articlesApi = PowerMockito.mock(ArticlesApi::class.java)
    private val repository = ArticlesRepositoryImpl(articlesApi)

    @Test
    fun `getArticles() is returning articles list if the api response is successful`() = runTest {
        // Given
        val response = Response.success(
            ArticlesResponse(
                results = listOf(
                    ArticleResult(title = "Article 1 title"),
                    ArticleResult(title = "Article 2 title"),
                )
            )
        )
        PowerMockito.doReturn(response).`when`(articlesApi).getMostPopularArticles()

        // When
        val articles = repository.getMostPopularArticles()

        // Then
        assertTrue(articles.size == 2)
        assertEquals("Article 1 title", articles[0].title)
        assertEquals("Article 2 title", articles[1].title)
    }

    @Test
    fun `getArticles() is returning empty list if the api response is empty`() = runTest {
        // Given
        val response = Response.success(
            ArticlesResponse(
                results = emptyList()
            )
        )
        PowerMockito.doReturn(response).`when`(articlesApi).getMostPopularArticles()

        // When
        val articles = repository.getMostPopularArticles()

        // Then
        assertTrue(articles.isEmpty())
    }

    @Test
    fun `getArticles() is throwing exception if the api response is 500`() = runTest {
        // Given
        val response = mockErrorResponse(code = 500)
        PowerMockito.doReturn(response).`when`(articlesApi).getMostPopularArticles()

        // When
        val exception = assertThrows(ErrorResponseException::class.java) {
            runBlocking {
                repository.getMostPopularArticles()
            }
        }

        // Then
        assertEquals(500, exception.statusCode)
    }

    @Test
    fun `getArticles() is throwing exception if the api response is 500 with message`() = runTest {
        // Given
        val json = "{\"message\": \"Unexpected error\"}"
        val response = mockErrorResponse(json = json, code = 400)
        PowerMockito.doReturn(response).`when`(articlesApi).getMostPopularArticles()

        // When
        val exception = assertThrows(ErrorResponseException::class.java) {
            runBlocking {
                repository.getMostPopularArticles()
            }
        }

        // Then
        assertEquals("Unexpected error", exception.message)
        assertEquals(400, exception.statusCode)
    }
}