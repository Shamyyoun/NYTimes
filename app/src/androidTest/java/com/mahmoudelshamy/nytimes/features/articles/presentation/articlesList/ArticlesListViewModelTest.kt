package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import com.mahmoudelshamy.nytimes.features.common.presentation.NavigationCommand
import com.mahmoudelshamy.nytimes.core.BaseAndroidTest
import com.mahmoudelshamy.nytimes.di.AppModule
import com.mahmoudelshamy.nytimes.features.articles.data.FakeArticlesData
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.domain.useCases.GetMostPopularArticlesUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ArticlesListViewModelTest : BaseAndroidTest() {
    @Inject
    lateinit var getMostPopularArticlesUseCase: GetMostPopularArticlesUseCase

    @Test
    fun view_model_is_changing_the_view_state_to_error_when_articles_api_failed() {
        runTest {
            // Given
            FakeArticlesData.articlesResponse = 400 to "{\"message\": \"api error\"}"

            // When
            val viewModel = ArticlesListViewModel(getMostPopularArticlesUseCase)

            // Then
            assertTrue(viewModel.viewState.value is ArticlesListViewState.Error)
            val viewState = viewModel.viewState.value as ArticlesListViewState.Error
            assertEquals("api error", viewState.message)
        }
    }

    @Test
    fun view_model_is_changing_the_view_state_to_articles_when_getArticlesUseCase_emits_articles_list() {
        runTest {
            // Given
            FakeArticlesData.articlesResponse = 200 to FakeArticlesData.articlesJson

            // When
            val viewModel = ArticlesListViewModel(getMostPopularArticlesUseCase)

            // Then
            assertTrue(viewModel.viewState.value is ArticlesListViewState.Articles)

            val viewState = viewModel.viewState.value as ArticlesListViewState.Articles
            assertTrue(viewState.articles.size == 4)
            assertEquals("Article 1", viewState.articles[0].title)
            assertEquals("Article 2", viewState.articles[1].title)
        }
    }

    @Test
    fun view_model_is_changing_the_view_state_to_empty_when_getArticlesUseCase_emits_empty_list() {
        runTest {
            // Given
            FakeArticlesData.articlesResponse = 200 to FakeArticlesData.emptyArticlesJson

            // When
            val viewModel = ArticlesListViewModel(getMostPopularArticlesUseCase)

            // Then
            assertTrue(viewModel.viewState.value is ArticlesListViewState.Empty)
        }
    }

    @Test
    fun view_model_is_navigating_to_article_details_when_ArticleClicked_event_happened() {
        runTest {
            // Given
            val event = ArticlesListEvent.ArticleClicked(
                article = Article(title = "Article 1")
            )

            // When
            val viewModel = ArticlesListViewModel(getMostPopularArticlesUseCase)
            viewModel.onEvent(event)

            // Then
            val navCommand = viewModel.navigation.value
            assertTrue(navCommand is NavigationCommand.ToDirection)

            val directions = (navCommand as NavigationCommand.ToDirection).direction
            assertTrue(directions is ArticlesListFragmentDirections.ActionNavArticlesListToNavArticleDetails)
        }
    }
}