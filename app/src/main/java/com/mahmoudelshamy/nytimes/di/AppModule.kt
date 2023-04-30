package com.mahmoudelshamy.nytimes.di

import com.mahmoudelshamy.nytimes.common.Constants
import com.mahmoudelshamy.nytimes.features.articles.data.remote.ArticlesApi
import com.mahmoudelshamy.nytimes.features.articles.data.remote.interceptors.AuthInterceptor
import com.mahmoudelshamy.nytimes.features.articles.data.repository.ArticlesRepositoryImpl
import com.mahmoudelshamy.nytimes.features.articles.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.END_POINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideArticlesApi(retrofit: Retrofit): ArticlesApi {
        return retrofit.create(ArticlesApi::class.java)
    }

    @Provides
    fun provideArticlesRepository(articlesApi: ArticlesApi): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesApi)
    }
}