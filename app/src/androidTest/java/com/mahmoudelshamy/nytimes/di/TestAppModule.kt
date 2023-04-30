package com.mahmoudelshamy.nytimes.di

import com.mahmoudelshamy.nytimes.features.articles.data.remote.FakeArticlesApi
import com.mahmoudelshamy.nytimes.features.articles.data.remote.ArticlesApi
import com.mahmoudelshamy.nytimes.features.articles.data.repository.ArticlesRepositoryImpl
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    fun provideArticlesApi(gson: Gson): ArticlesApi {
        return FakeArticlesApi(gson)
    }

    @Provides
    fun provideArticlesRepository(articlesApi: ArticlesApi): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesApi)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}