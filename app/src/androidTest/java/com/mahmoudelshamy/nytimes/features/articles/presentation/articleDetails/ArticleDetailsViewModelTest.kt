package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails

import com.mahmoudelshamy.nytimes.core.BaseAndroidTest
import com.mahmoudelshamy.nytimes.di.AppModule
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ArticleDetailsViewModelTest : BaseAndroidTest() {
    private val viewModel =
        ArticleDetailsViewModel()

    @Test
    fun view_model_is_changing_the_view_state_article_state_when_article_is_set() {
        runTest {
            // Given
            val article = Article(
                id = 199,
                title = "Fifa world cup was great!"
            )

            // When
            viewModel.init(article)

            // Then
            assertTrue(viewModel.viewState.value is ArticleDetailsViewState.ArticleDetails)
            val emittedArticle = (viewModel.viewState.value as ArticleDetailsViewState.ArticleDetails).article
            assertEquals(199, emittedArticle.id)
            assertEquals("Fifa world cup was great!", emittedArticle.title)
        }
    }
}