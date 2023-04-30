package com.mahmoudelshamy.nytimes.features.articles.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

@Keep
data class ArticlesResponse(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("num_results")
    val numResults: Int = 0,
    @SerializedName("results")
    val results: List<ArticleResult> = listOf(),
    @SerializedName("status")
    val status: String = ""
)

@Keep
data class ArticleResult(
    @SerializedName("abstract")
    val _abstract: String = "",
    @SerializedName("adx_keywords")
    val adxKeywords: String = "",
    @SerializedName("asset_id")
    val assetId: Long = 0,
    @SerializedName("byline")
    val byline: String = "",
    @SerializedName("column")
    val column: Any = Any(),
    @SerializedName("des_facet")
    val desFacet: List<String> = listOf(),
    @SerializedName("eta_id")
    val etaId: Int = 0,
    @SerializedName("geo_facet")
    val geoFacet: List<String> = listOf(),
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("media")
    val media: List<ArticleMedia> = listOf(),
    @SerializedName("nytdsection")
    val nytdSection: String = "",
    @SerializedName("org_facet")
    val orgFacet: List<String> = listOf(),
    @SerializedName("per_facet")
    val perFacet: List<String> = listOf(),
    @SerializedName("published_date")
    val publishedDate: String = "",
    @SerializedName("section")
    val section: String = "",
    @SerializedName("source")
    val source: String = "",
    @SerializedName("subsection")
    val subsection: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("updated")
    val updated: String = "",
    @SerializedName("uri")
    val uri: String = "",
    @SerializedName("url")
    val url: String = ""
)

@Keep
data class ArticleMedia(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int = 0,
    @SerializedName("caption")
    val caption: String = "",
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("media-metadata")
    val mediaMetadata: List<ArticleMediaMetadata> = listOf(),
    @SerializedName("subtype")
    val subtype: String = "",
    @SerializedName("type")
    val type: String = ""
)

@Keep
data class ArticleMediaMetadata(
    @SerializedName("format")
    val format: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)

fun ArticleResult.toArticle(): Article {
    val imageMedia = media.find { it.type == "image" }

    return Article(
        id = id,
        title = title,
        content = _abstract,
        persons = perFacet,
        publishedAt = publishedDate,
        thumbnailUrl = imageMedia?.mediaMetadata?.firstOrNull()?.url,
        imageUrl = imageMedia?.mediaMetadata?.lastOrNull()?.url
    )
}