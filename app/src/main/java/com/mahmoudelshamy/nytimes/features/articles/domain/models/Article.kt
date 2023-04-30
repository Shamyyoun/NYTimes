package com.mahmoudelshamy.nytimes.features.articles.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Long = 0L,
    val title: String = "",
    val content: String = "",
    val persons: List<String> = emptyList(),
    val publishedAt: String = "",
    val thumbnailUrl: String? = null,
    val imageUrl: String? = null
) : Parcelable
