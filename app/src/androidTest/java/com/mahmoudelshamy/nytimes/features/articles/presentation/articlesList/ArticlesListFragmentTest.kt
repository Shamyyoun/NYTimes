package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.core.MainActivity
import com.mahmoudelshamy.nytimes.di.AppModule
import com.mahmoudelshamy.nytimes.features.articles.data.FakeArticlesData
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.ArticlesResponse
import com.mahmoudelshamy.nytimes.features.articles.data.remote.dto.toArticle
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.google.gson.Gson
import com.mahmoudelshamy.nytimes.core.BaseAndroidTest
import com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList.widgets.ArticlesAdapter
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ArticlesListFragmentTest : BaseAndroidTest() {
    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var gson: Gson

    private val articleInTestIndex = 1
    private lateinit var articleInTest: Article

    @Before
    override fun setUp() {
        super.setUp()

        articleInTest = gson.fromJson(
            FakeArticlesData.articlesJson,
            ArticlesResponse::class.java
        ).results[articleInTestIndex].toArticle()
    }

    @Test
    fun ensure_that_articles_recycler_is_displayed_and_other_views_not_onAppLaunch() {
        // Assert articles recycler is visible and other view not
        onView(withId(R.id.widget_articles)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_articles)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_articles_empty_msg)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_articles_error_msg)).check(matches(not(isDisplayed())))
    }

    @Test
    fun ensure_that_ArticleDetailsFragment_is_displayed_when_clicking_on_article_item() {
        // Click on article item
        onView(withId(R.id.rv_articles))
            .perform(
                actionOnItemAtPosition<ArticlesAdapter.ViewHolder>(
                    articleInTestIndex,
                    click()
                )
            )

        // Assert that details fragment appeared and rendered the article
        onView(withId(R.id.tv_article_title)).check(matches(withText(articleInTest.title)))
        onView(withId(R.id.tv_article_content)).check(matches(withText(articleInTest.content)))
        onView(withId(R.id.tv_article_date)).check(matches(withText(articleInTest.publishedAt)))
        val authors = articleInTest.persons.joinToString(", ")
        val authorsText = context.getString(R.string.by_s, authors)
        onView(withId(R.id.tv_article_authors)).check(matches(withText(authorsText)))
        onView(withId(R.id.tv_article_authors)).check(matches(isDisplayed()))
    }
}