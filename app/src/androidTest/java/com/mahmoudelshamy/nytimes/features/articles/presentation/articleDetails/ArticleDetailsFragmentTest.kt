package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.gson.Gson
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.core.BaseAndroidTest
import com.mahmoudelshamy.nytimes.core.launchFragmentInHiltContainer
import com.mahmoudelshamy.nytimes.di.AppModule
import com.mahmoudelshamy.nytimes.features.articles.data.FakeArticlesData
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticlesResponse
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.toArticle
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ArticleDetailsFragmentTest : BaseAndroidTest() {
    @Inject
    lateinit var gson: Gson

    @Test
    fun ensure_that_ArticleDetailsFragment_is_displayed_and_renders_article_details() {
        // Given
        val articleInTest = gson.fromJson(
            FakeArticlesData.articlesJson,
            ArticlesResponse::class.java
        ).results[2].toArticle()

        // Launch the fragment
        launchFragmentInHiltContainer<ArticleDetailsFragment>(
            fragmentArgs = bundleOf ("article" to articleInTest)
        )

        // Validate screen content
        onView(withId(R.id.tv_article_title)).check(matches(withText(articleInTest.title)))
        onView(withId(R.id.tv_article_content)).check(matches(withText(articleInTest.content)))
        onView(withId(R.id.tv_article_date)).check(matches(withText(articleInTest.publishedAt)))
        val authors = articleInTest.persons.joinToString(", ")
        val authorsText = context.getString(R.string.by_s, authors)
        onView(withId(R.id.tv_article_authors)).check(matches(withText(authorsText)))
    }

    @Test
    fun ensure_that_authors_text_is_visible_when_article_has_persons() {
        // Given
        val articleInTest = gson.fromJson(
            FakeArticlesData.articlesJson,
            ArticlesResponse::class.java
        ).results[2].toArticle()

        // Launch the fragment
        launchFragmentInHiltContainer<ArticleDetailsFragment>(
            fragmentArgs = bundleOf ("article" to articleInTest)
        )

        // Validate
        onView(withId(R.id.tv_article_authors)).check(matches(isDisplayed()))
    }

    @Test
    fun ensure_that_authors_text_is_hidden_when_article_does_not_have_persons() {
        // Given
        val articleInTest = gson.fromJson(
            FakeArticlesData.articlesJson,
            ArticlesResponse::class.java
        ).results[3].toArticle()

        // Launch the fragment
        launchFragmentInHiltContainer<ArticleDetailsFragment>(
            fragmentArgs = bundleOf ("article" to articleInTest)
        )

        // Validate
        onView(withId(R.id.tv_article_authors)).check(matches(not(isDisplayed())))
    }
}